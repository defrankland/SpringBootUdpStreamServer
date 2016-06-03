package com.springstreamudp.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springstreamudp.application.UdpClient;

@Controller
public class SiteController {
	
	@RequestMapping("/connect")
	public String connectPage(){
		return "index";
	}
	
	@RequestMapping(value="/start", method = RequestMethod.POST)
	public String startUdp() {

		try {
			UdpClient.requestUdpConnection();
		} catch (IOException e) {
			System.out.println("Failed to connect, " + e);
		}
		
		return "index";
	}
}
