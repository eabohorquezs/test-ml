package com.mercadolibre.ipmonitor;

import static spark.Spark.awaitInitialization;
import static spark.Spark.port;

import com.mercadolibre.ipmonitor.dao.BasicDao;
import com.mercadolibre.ipmonitor.router.IPAddressRouter;
import com.mercadolibre.ipmonitor.service.IpServiceImpl;

public class App {  
	
    public static void main(String[] args) {
    	port(3000);
    	
    	BasicDao.initializeDB();
    	
    	new IPAddressRouter(new IpServiceImpl());
    	
    	awaitInitialization();    	
    }
}
