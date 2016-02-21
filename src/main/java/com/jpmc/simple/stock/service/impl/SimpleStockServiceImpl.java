package com.jpmc.simple.stock.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jpmc.simple.stock.constant.TradeIndicator;
import com.jpmc.simple.stock.model.Stock;
import com.jpmc.simple.stock.model.Trade;
import com.jpmc.simple.stock.service.SimpleStockService;

/**
 * 
 * @author Suresh
 * 
 * This class contains all the operation on the stocks given in the requirements
 *
 */

@Service
public class SimpleStockServiceImpl implements SimpleStockService {

	private Logger log = Logger.getLogger(SimpleStockServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.jpmc.simple.stock.service.SimpleStockService#calcuateShareIndex(java.util.Map)
	 * 
	 * This method will return the Calculated Share index
	 */
	public Double calcuateShareIndex(Map<String, Stock> stocks) throws Exception {
		Double allShareIndex = 0.0;
		for(Stock stock: stocks.values()) {
			allShareIndex+= getLastTradedPrice(stock);
		}
		return Math.pow(allShareIndex, 1.0 / stocks.size());
	}
	
	
	/* (non-Javadoc)
	 * @see com.jpmc.simple.stock.service.SimpleStockService#calculateDividend(com.jpmc.simple.stock.model.Stock, java.lang.Double)
	 * 
	 * This method is to calculate the Dividend of a Stock
	 */
	public Double calculateDividend(Stock stock, Double price) throws Exception {
		log.info("Calculating the Dividend for the stock : " + stock);
		switch(stock.getType()) {
			case COMMON:
				return stock.getLastDividend()/price;
			case PREFERRED:
				return stock.getFixedDividend() * stock.getParValue()/ price;
			default:
				return 0.0;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.jpmc.simple.stock.service.SimpleStockService#calculatePERatio(com.jpmc.simple.stock.model.Stock, java.lang.Double)
	 * 
	 * This method is used to calculate PER Ration
	 * 
	 */
	public Double calculatePERatio(Stock stock, Double price) throws Exception {
		log.info("Calculating the PERation for the stock : "+stock);
		return price/stock.getLastDividend();
	}

	/* (non-Javadoc)
	 * @see com.jpmc.simple.stock.service.SimpleStockService#buyStock(com.jpmc.simple.stock.model.Stock, java.lang.Integer, java.lang.Double)
	 * 
	 * This method will perform the operation of stock buying
	 * 
	 */
	public void buyStock(Stock stock, Integer quantity, Double price) throws Exception {
		
		Trade trade = new Trade(TradeIndicator.BUY, quantity, price);
		stock.getTrades().put(new Date(), trade);
		
		log.info(quantity+ " Stock of symbol \""+stock.getSymbol() + "\" has been bought with "+price +" price.");
	}

	/* (non-Javadoc)
	 * @see com.jpmc.simple.stock.service.SimpleStockService#sellStock(com.jpmc.simple.stock.model.Stock, java.lang.Integer, java.lang.Double)
	 * 
	 * * This method will perform the operation of stock selling
	 * 
	 */
	public void sellStock(Stock stock, Integer quantity, Double price) throws Exception {
		Trade trade = new Trade(TradeIndicator.SELL, quantity, price);
		stock.getTrades().put(new Date(), trade);
		
		log.info(quantity+ " Stock \""+stock.getSymbol() + "\" has been sold with "+price +" price.");
	}
	
	/* (non-Javadoc)
	 * @see com.jpmc.simple.stock.service.SimpleStockService#getStockPrice(com.jpmc.simple.stock.model.Stock)
	 * This method will return the price of the stock last sold price 
	 * 
	 */
	public Double getStockPrice(Stock stock) throws Exception {
		if (stock.getTrades().size() > 0) {
			// Uses the last traded price as price
			return stock.getTrades().lastEntry().getValue().getPrice();
		} else {
			return 0.0;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.jpmc.simple.stock.service.SimpleStockService#calculateVolumeWeightedStockPrice(com.jpmc.simple.stock.model.Stock)
	 * 
	 * This method will perform the operation of calculating Volume weighted Stock price
	 * 
	 */
	public Double calculateVolumeWeightedStockPrice(Stock stock, int pastMinutesToCal) throws Exception  {
		Date now = new Date();
		// Time 15 minutes ago
		Date startTime = new Date(now.getTime() - (pastMinutesToCal * 60 * 1000));
		// Get trades for the last 15 minutes
		SortedMap<Date, Trade> trades = stock.getTrades().tailMap(startTime);
		Double volumeWeigthedStockPrice = 0.0;
		Integer totalQuantity = 0;
		for (Trade trade: trades.values()) {
			totalQuantity += trade.getQuantity();
			volumeWeigthedStockPrice += trade.getPrice() * trade.getQuantity();
		}
		return volumeWeigthedStockPrice/totalQuantity;
	}
	
	/**
	 * @return
	 */
	public Double getLastTradedPrice(Stock stock) {
		if (stock.getTrades().size() > 0) {
			// Uses the last trade price as price
			return stock.getTrades().lastEntry().getValue().getPrice();
		} else {
			return 0.0;
		}
	}

}
