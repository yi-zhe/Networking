package com.networking.ch7;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Random;
// server socket channel time server
public class SocketChannelTimeClient {

	public static void main(String[] args) {
		Random random = new Random();
		SocketAddress address = new InetSocketAddress("127.0.0.1", 5000);
		try (SocketChannel socketChannel = SocketChannel.open(address)) {
			while (true) {
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

				Thread.sleep(random.nextInt(1000) + 1000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
