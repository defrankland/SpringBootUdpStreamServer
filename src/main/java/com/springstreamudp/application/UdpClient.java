package com.springstreamudp.application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.List;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import com.springstreamudp.model.*;

@Component
public class UdpClient {
	
	private static InetSocketAddress svrUdpSock = new InetSocketAddress("192.168.1.177", 8888);
	private static ClientFacade board = new ArduinoBoard();
	private int[] latestUdpData;
	
	@ServiceActivator
	public void getStreamData(byte[] message){
		 
		 updateServerData(message);
		 adaptData();
		 
		 System.out.println("UdpStream data stored: " + board.toString());
		 
		 try {
			 renewConnection();
		} catch (IOException e) {
			System.out.println("Connection attempt failed" + e);
		}
	}
	
	static public void requestUdpConnection() throws IOException{

		byte[] udpDataReq = new byte[]{121, 11};
		DatagramSocket svrSock;
		DatagramPacket svrReqPacket;
		
		try {
			svrReqPacket = new DatagramPacket(udpDataReq, udpDataReq.length, svrUdpSock);
			svrSock = new DatagramSocket();
			svrSock.send(svrReqPacket);
			System.out.println("sent");
			svrSock.close();
		} catch (SocketException e) {
			System.out.println("Error on UDP_CLIENT_REQ " + e);
		}
	}
	
	private void renewConnection() throws IOException{
		byte[] udpDataReq = new byte[]{121,12};
		DatagramSocket svrSock;		
		DatagramPacket svrReqPacket;
		
		try {
			svrReqPacket = new DatagramPacket(udpDataReq, udpDataReq.length, svrUdpSock);
			svrSock = new DatagramSocket();
			svrSock.send(svrReqPacket);
			svrSock.close();
		} catch (SocketException e) {
			System.out.println(e);
		}
	}
	
	private void updateServerData(byte[] payload){
		
		latestUdpData = new int[payload.length];
		
		for (int i = 0; i < latestUdpData.length; i++){
			
			//convert byte to int
			latestUdpData[i] = payload[i];
			
			//un-sign
			if(latestUdpData[i] < 0){
				latestUdpData[i] = 127 + -latestUdpData[i];
			}
		}
	}
	
	public void adaptData() {
		
		List<? extends ServerData<?>> data = board.getData();
		
		if (latestUdpData != null){
			int i = 0;
			for(ServerData<?> d: data){
				int val = latestUdpData[i];
				board.setData(d.getId(), d.getType(), val);
				i++;
			}
		}
	}
	
	public static ClientFacade getLatestData(){
		return board;
	}
}
