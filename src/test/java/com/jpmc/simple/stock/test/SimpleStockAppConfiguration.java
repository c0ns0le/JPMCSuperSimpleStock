package com.jpmc.simple.stock.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jpmc.simple.stock.repo.StockRepository;
import com.jpmc.simple.stock.repo.impl.StockRepositoryImpl;
import com.jpmc.simple.stock.service.SimpleStockService;
import com.jpmc.simple.stock.service.impl.SimpleStockServiceImpl;

@Configuration
public class SimpleStockAppConfiguration {
	
	@Bean
    public SimpleStockService simpleStockService() {
        return new SimpleStockServiceImpl();
    }
	
	@Bean
    public StockRepository stockRepository() {
        return new StockRepositoryImpl();
    }

}
