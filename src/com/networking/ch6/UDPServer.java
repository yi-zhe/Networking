package com.networking.ch6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {
	
	public UDPServer() {
		System.out.println("UDP Server Started");
		
		try(DatagramSocket serverSocket = new DatagramSocket(9000)) {
			
			while(true) {
				byte[] receiveMessage = new byte[1024];
				DatagramPacket packet = new DatagramPacket(receiveMessage, receiveMessage.length);
				serverSocket.receive(packet);
				
				String message = new String(packet.getData());
				System.out.println("Received from client: [" + message +"]\nFrom: " + packet.getAddress());
				
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				byte[] sendMessage = message.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendMessage, sendMessage.length, address, port);
				serverSocket.send(sendPacket);
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("UDP Server Terminating");
	}

	public static void main(String[] args) {
        new UDPServer();
	}

}
