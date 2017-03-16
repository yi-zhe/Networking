package com.networking.ch6;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Collections;
import java.util.Enumeration;

public class UDPDatagramMulticastServer {

	private static void networkInterfaces() {
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
				System.out.printf("Display name: %s\n", networkInterface.getDisplayName());
				System.out.printf("Name: %s\n", networkInterface.getName());
				System.out.printf("Supports Multicast: %s\n", networkInterface.supportsMulticast());
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
				for (InetAddress inetAddress : Collections.list(inetAddresses)) {
					System.out.printf("InetAddress: %s\n", inetAddress);
				}
				System.out.println();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			System.setProperty("java.net.preferIPv6Stack", "true");
			DatagramChannel channel = DatagramChannel.open();

			NetworkInterface networkInterface = NetworkInterface.getByName("eth3");
			channel.setOption(StandardSocketOptions.IP_MULTICAST_IF, networkInterface);
			InetSocketAddress group = new InetSocketAddress("fe80::8517:aebd:81c9:51a2%12", 9003);
			String message = "The message";
			ByteBuffer buffer = ByteBuffer.allocate(message.length());
			buffer.put(message.getBytes());

			while (true) {
				channel.send(buffer, group);
				System.out.println("Sent the multicast message:" + message);
				buffer.clear();
				buffer.mark();
				System.out.print("sent: [");
				StringBuilder builder = new StringBuilder();
				while (buffer.hasRemaining()) {
					builder.append((char) (buffer.get()));
				}
				System.out.println(builder + "]");
				buffer.reset();
				Thread.sleep(3000);
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
