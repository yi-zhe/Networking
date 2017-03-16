package com.networking.ch6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMulticastClient {

	public UDPMulticastClient() {
		System.out.println("UDPMulticast Client started");

		try {
			MulticastSocket multicastSocket = new MulticastSocket(9877);
			InetAddress address = InetAddress.getByName("228.5.6.7");
			multicastSocket.joinGroup(address);

			byte[] data = new byte[256];
			DatagramPacket packet = new DatagramPacket(data, data.length);

			while (true) {
				multicastSocket.receive(packet);
				String message = new String(packet.getData(), 0, packet.getLength());
				System.out.println("Message from: " + packet.getAddress() + " Message :" + message);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("UDPMulticastClient terminated");
	}

	public static void main(String[] args) {
		new UDPMulticastClient();
	}

}
