package com.networking.ch6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPEchoServer {

	public static void main(String[] args) {
		int port = 9000;
		System.out.println("UDP Echo Server started");

		try (DatagramChannel channel = DatagramChannel.open()) {
			DatagramSocket socket = channel.socket();

			SocketAddress address = new InetSocketAddress(port);
			socket.bind(address);

			ByteBuffer buffer = ByteBuffer.allocateDirect(65507);
			while (true) {
				SocketAddress client = channel.receive(buffer);
				buffer.flip();
				buffer.mark();

				System.out.print("Received: [");
				StringBuilder builder = new StringBuilder();
				while (buffer.hasRemaining()) {
					builder.append((char) buffer.get());
				}
				System.out.println(builder + "]");
				buffer.reset();

				channel.send(buffer, client);
				System.out.println("Sent: [" + builder + "]");
				buffer.clear();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("UDP Echo Server terminated");
	}

}
