package com.jpmc.simple.stock.repo.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.jpmc.simple.stock.constant.StockType;
import com.jpmc.simple.stock.model.Stock;
import com.jpmc.simple.stock.repo.StockRepository;
import com.jpmc.simple.stock.service.impl.SimpleStockServiceImpl;

/**
 * Stock Repository, to get the stock from the back end, implementation can be changed as per the backend usage.
 * 
 * @author Suresh
 */
public class StockRepositoryImpl implements StockRepository {
	
	 
	 private Logger log = Logger.getLogger(SimpleStockServiceImpl.class);
	 
	 HashMap<String, Stock> testStockList;
	 
     /* (non-Javadoc)
     * @see com.jpmc.simple.stock.repo.StockRepository#getAllStocks()
     * 
     * This method will return all the stocks which is added in this system
     * This implementation can be changed to get the data from database or any storage
     * 
     */
    public HashMap<String, Stock> getAllStocks(){
    	 
    	 log.info("Selecting all stocks");
    	 testStockList = new HashMap<String, Stock>();
    	 
    	 testStockList.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
         testStockList.put("POP", new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0));
         testStockList.put("ALE", new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
         testStockList.put("GIN", new Stock("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
         testStockList.put("JOE", new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
         
         log.info("No. of stocks selected are : "+testStockList.size());
         
         return testStockList;
         
     }
     
     public Stock getStock(String stockSybmol) throws Exception{
    	 
    	 if(testStockList !=null && testStockList.size() > 0) {
    		 return testStockList.get(stockSybmol);
    	 }
    	 throw new Exception("No Stock is found with the given name: "+stockSybmol);
    	 
     }

}
