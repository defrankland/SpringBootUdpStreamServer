package com.springstreamudp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springstreamudp.application.UdpClient;
import com.springstreamudp.model.ClientFacade;

@RestController
public class RestConsumerController {

	@RequestMapping("/udpstream")
    public ClientFacade udpData() {
		return UdpClient.getLatestData();
    }
}
