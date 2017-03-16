package com.networking.ch6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

public class UDPMulticastServer {

	public UDPMulticastServer() {
		System.out.println("UDPMulticast Server Started");

		try {
			MulticastSocket multicastSocket = new MulticastSocket();
			InetAddress address = InetAddress.getByName("228.5.6.7");
			multicastSocket.joinGroup(address);

			byte[] data;
			DatagramPacket packet;
			while (true) {
				Thread.sleep(3000);
				String message = (new Date()).toString();
				data = message.getBytes();
				packet = new DatagramPacket(data, data.length, address, 9877);
				multicastSocket.send(packet);
				System.out.println("sent: " + message);
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("UDPMulticast Server Terminated");
	}

	public static void main(String[] args) {
		new UDPMulticastServer();
	}

}
