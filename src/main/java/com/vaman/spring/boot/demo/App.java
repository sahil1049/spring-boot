package com.vaman.spring.boot.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Vaman Deshmukh 
 *
 */

@SpringBootApplication
public class App {
	 static Logger logger =LoggerFactory.getLogger(App.class) ;
	 
	public static void main(String[] args) {
        logger.info("Strt");
		
		SpringApplication.run(App.class, args);
		System.out.println("End");
	}
}
