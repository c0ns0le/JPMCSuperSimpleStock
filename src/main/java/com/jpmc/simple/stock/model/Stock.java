package com.jpmc.simple.stock.model;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.jpmc.simple.stock.constant.StockType;
import com.jpmc.simple.stock.constant.TradeIndicator;


/**
 * Stock Bean
 * 
 * @author Suresh *
 */
public class Stock {
	
	private String symbol;
	private StockType type;
	private Double lastDividend;
	private Double fixedDividend;
	private Double parValue;
    private TreeMap<Date, Trade> trades;
    
	/**
	 * @return
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return
	 */
	public StockType getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(StockType type) {
		this.type = type;
	}

	/**
	 * @return
	 */
	public Double getLastDividend() {
		return lastDividend;
	}

	/**
	 * @param lastDividend
	 */
	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	/**
	 * @return
	 */
	public Double getFixedDividend() {
		return fixedDividend;
	}

	/**
	 * @param fixedDividend
	 */
	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	/**
	 * @return
	 */
	public Double getParValue() {
		return parValue;
	}

	/**
	 * @param parValue
	 */
	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	/**
	 * @return
	 */
	public TreeMap<Date, Trade> getTrades() {
		return trades;
	}

	/**
	 * @param trades
	 */
	public void setTrades(TreeMap<Date, Trade> trades) {
		this.trades = trades;
	}

	/**
	 * @param symbol
	 * @param type
	 * @param lastDividend
	 * @param fixedDividend
	 * @param parValue
	 */
	public Stock(String symbol, StockType type, Double lastDividend, Double fixedDividend, Double parValue) {
		this.setSymbol(symbol);
		this.setType(type);
		this.setLastDividend(lastDividend);
		this.setFixedDividend(fixedDividend);
		this.setParValue(parValue);
		this.trades = new TreeMap<Date, Trade>();
	}

	@Override
	public String toString() {
		String pattern = "Stock Object [stockSymbol: %s, stockType: %s, lastDividend: %8.0f, fixedDividend: %8.2f, parValue: %8.2f]";
		return String.format(pattern, symbol, type, lastDividend, fixedDividend, parValue);
	}

	
	
	
}
