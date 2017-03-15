package com.networking.ch6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPClient {
	
	public UDPClient() {
		System.out.println("UDPClient Started");
		
		Scanner scanner = new Scanner(System.in);
		try (DatagramSocket socket = new DatagramSocket()) {
			
			InetAddress address = InetAddress.getByName("localhost");
			byte[] sendMessage;
			while(true) {
				System.out.println("Enter a message:");
				String message = scanner.nextLine();
				if("quit".equalsIgnoreCase(message)) {
					break;
				}
				
				sendMessage = message.getBytes();
				DatagramPacket packet = new DatagramPacket(sendMessage, sendMessage.length, address, 9000);
				socket.send(packet);
				
				byte[] receiveMessage = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveMessage, receiveMessage.length);
				socket.receive(receivePacket);
				String receivedSentence = new String(receivePacket.getData());

				System.out.println("Received from server: [" + receivedSentence +"]\nFrom: " + receivePacket.getSocketAddress());
				
			}
			
			socket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("UDPClient terminating");
	}

	public static void main(String[] args) {
       new UDPClient();
	}

}
