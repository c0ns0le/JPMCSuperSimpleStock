package com.jpmc.simple.stock.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jpmc.simple.stock.constant.StockType;
import com.jpmc.simple.stock.constant.TradeIndicator;
import com.jpmc.simple.stock.model.Stock;
import com.jpmc.simple.stock.model.Trade;
import com.jpmc.simple.stock.repo.StockRepository;
import com.jpmc.simple.stock.service.SimpleStockService;

/**
 * @author Suresh
 * 
 * Junit Test cases for Super Simple Stock
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SimpleStockAppConfiguration.class })
public class SimpleStockServicesTest {

	Logger log = Logger.getLogger(SimpleStockServicesTest.class);

	@Autowired
	SimpleStockService simpleStockService;
	
	@Autowired
	StockRepository stockRepository;
	
	@Test
	public void testAllShareIndex() {
        HashMap<String, Stock> db = stockRepository.getAllStocks();
        
        for (Stock stock: db.values()) {
            // Record some trades
        	for (int i=1; i <= 10; i++) {
        		try {
					simpleStockService.buyStock(stock,3, 2.0);
					simpleStockService.sellStock(stock,3, 2.0);
				} catch (Exception e) {
					log.error("Excpetion in testAllShareIndex. ", e);
					Assert.assertTrue(false);
				}	
        	}
        }
        Double GBCEallShareIndex = 0.0d;
		try {
			GBCEallShareIndex = simpleStockService.calcuateShareIndex(db);
		} catch (Exception e) {
			log.error("Excpetion in testAllShareIndex. ", e);
			Assert.assertTrue(false);
		}
        assertEquals(1.5848931924611136, GBCEallShareIndex, 0.0);
        log.info("testAllShareIndex test Success!!");
	}

	@Test
	public void testDividend() {
        Stock stockPOP = new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0);
        Stock stockGIN = new Stock("GIN", StockType.PREFERRED, 8.0, 0.02, 100.0);
        // Test dividend for Common method
		Double dividendPOP = 0.0;
		try {
			dividendPOP = simpleStockService.calculateDividend(stockPOP,1.0);
		} catch (Exception e) {
			log.error("Excpetion in testDividend. ", e);
			Assert.assertTrue(false);
		}
		Double expectedDividendALE = stockPOP.getLastDividend()/1.0;
		assertEquals(expectedDividendALE, dividendPOP, 0.0);
		// Test dividend for Preferred method
		Double dividendGIN = 0.0;
		try {
			dividendGIN = simpleStockService.calculateDividend(stockGIN, 1.0);
		} catch (Exception e) {
			log.error("Excpetion in testDividend. ", e);
			Assert.assertTrue(false);
			
		}
		Double expectedDividendGIN = stockGIN.getFixedDividend() * stockGIN.getParValue() / 1.0;
		assertEquals(expectedDividendGIN, dividendGIN, 0.0);
		
		log.info("testDividend test Success!!");
	}

	@Test
	public void testPERatio() {
        Stock stockJOE = new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0);
        Double peRatioJOE = 0.0d;
		try {
			peRatioJOE = simpleStockService.calculatePERatio(stockJOE, 1.0);
		} catch (Exception e) {
			log.error("Excpetion in testPERatio. ", e);
			Assert.assertTrue(false);
			
		}
        Double expectedPeRatioALE = 1.0 / stockJOE.getLastDividend();
        
        assertEquals(expectedPeRatioALE, peRatioJOE, 0.0);
        
        log.info("testPERatio test Success!!");
	}

	@Test
	public void testStockBuying() {
		Stock stockPOP = new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0);
		try {
			simpleStockService.buyStock(stockPOP, 1, 10.0);
		} catch (Exception e) {
			log.error("Excpetion in testBuying. ", e);
			Assert.assertTrue(false);
		}
		assertEquals(10.0, simpleStockService.getLastTradedPrice(stockPOP), 0.0);
		
		log.info("testStockBuying test Success!!");
	}

	@Test
	public void testStockSelling() {
		Stock stockPOP = new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0);
		try {
			simpleStockService.sellStock(stockPOP, 1, 10.0);
		} catch (Exception e) {
			log.error("Excpetion in testSellSelling. ", e);
			Assert.assertTrue(false);
		}
		assertEquals(10.0, simpleStockService.getLastTradedPrice(stockPOP), 0.0);
		
		log.info("testStockSelling test Success!!");   
	}

	@Test
	public void testGetStocckPrice() {
		Stock stockTEA = new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0);
		try {
			simpleStockService.sellStock(stockTEA, 1, 10.0);
		} catch (Exception e) {
			log.error("Excpetion in testGetPrice. ", e);
			Assert.assertTrue(false);
		}
		try {
			simpleStockService.buyStock(stockTEA, 1, 11.0);
		} catch (Exception e) {
			log.error("Excpetion in testGetPrice. ", e);
			Assert.assertTrue(false);
		}
		assertEquals(11.0, simpleStockService.getLastTradedPrice(stockTEA), 0.0);
		
		log.info("testGetStocckPrice test Success!!");
	}

	@Test
	public void testCalculateVolumeWeightedStockPrice() {
		Stock stockALE = new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		
		try {
			simpleStockService.sellStock(stockALE,2, 11.5);
			simpleStockService.buyStock(stockALE,2, 11.5);
		} catch (Exception e) {
			log.error("Excpetion in testAllShareIndex. ", e);
			Assert.assertTrue(false);
		}
		
		Double volumeWeightedStockPrice = 0.0d;
		int pastMinutesToCal = 15;
		try {
			volumeWeightedStockPrice = simpleStockService.calculateVolumeWeightedStockPrice(stockALE, pastMinutesToCal);
		} catch (Exception e) {
			log.error("Excpetion in testCalculateVolumeWeightedStockPrice. ", e);
			Assert.assertTrue(false);
		}
		assertEquals(11.5, volumeWeightedStockPrice, 0.0);
		Date now = new Date();
		// to get the past 15 mins trade
		Date startTime = new Date(now.getTime() - (16 * 60 * 1000));
		stockALE.getTrades().put(startTime, new Trade(TradeIndicator.BUY, 1, 20.0));
		
		try {
			volumeWeightedStockPrice = simpleStockService.calculateVolumeWeightedStockPrice(stockALE, pastMinutesToCal);
		} catch (Exception e) {
			log.error("Excpetion in testCalculateVolumeWeightedStockPrice. ", e);
			Assert.assertTrue(false);
		}
		assertEquals(11.5, volumeWeightedStockPrice, 0.0);
		
		log.info("testCalculateVolumeWeightedStockPrice test Success!!");
	}
	
}
