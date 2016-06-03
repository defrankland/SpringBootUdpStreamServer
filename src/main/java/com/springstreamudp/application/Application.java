package com.springstreamudp.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan({"com.springstreamudp.controller"})
@ImportResource("/configuration/udp-integration.xml")
public class Application {
	
    public static void main(String[] args) {
    	Object[] sources = {Application.class};
        SpringApplication.run(sources, args);
    }
}