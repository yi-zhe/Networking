package com.networking.ch3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelTimeClient {

	public static void main(String[] args) {
		SocketAddress address = new InetSocketAddress("127.0.0.1", 5000);
		try (SocketChannel socketChannel = SocketChannel.open(address)) {
			ByteBuffer buffer = ByteBuffer.allocate(64);
			int bytesRead = socketChannel.read(buffer);
			while (bytesRead > 0) {
				buffer.flip();
				while (buffer.hasRemaining()) {
					System.out.print((char) buffer.get());
				}
				System.out.println();
				bytesRead = socketChannel.read(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
