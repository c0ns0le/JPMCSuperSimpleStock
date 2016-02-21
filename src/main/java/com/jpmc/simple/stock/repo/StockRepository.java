package com.jpmc.simple.stock.repo;

import java.util.HashMap;

import com.jpmc.simple.stock.model.Stock;

/**
 * @author Suresh
 *
 */
public interface StockRepository {
	
	/**
	 * @return
	 */
	public HashMap<String, Stock> getAllStocks();
	
	/**
	 * @param stockSybmol
	 * @return
	 * @throws Exception
	 */
	public Stock getStock(String stockSybmol) throws Exception;
}
