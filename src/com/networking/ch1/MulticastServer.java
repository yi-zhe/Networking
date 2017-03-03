package com.networking.ch1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

public class MulticastServer {

	public static void main(String[] args) {
		System.out.println("Multicast Time Server");
		DatagramSocket serverSocket = null;
		try {
			serverSocket = new DatagramSocket();

			while (true) {
				String dateText = new Date().toString();
				byte[] buffer = dateText.getBytes();

				InetAddress group = InetAddress.getByName("230.0.0.0");
				DatagramPacket packet;
				packet = new DatagramPacket(buffer, buffer.length, group, 8888);
			    serverSocket.send(packet);
                System.out.println("Time sent: " + dateText);

                try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
