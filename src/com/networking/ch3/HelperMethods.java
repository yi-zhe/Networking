package com.networking.ch3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class HelperMethods {

	public static void sendFixedLengthMessage(SocketChannel socketChannel, String string) {
        try {
        	ByteBuffer buffer = ByteBuffer.allocate(64);
        	buffer.put(string.getBytes());
        	buffer.flip();
        	while(buffer.hasRemaining()) {
        		socketChannel.write(buffer);
        	}
        	System.out.println("Sent: " + string);
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}

	public static String receiveFixedLengthMessage(SocketChannel socketChannel) {
		StringBuilder message = new StringBuilder();
		try {
			ByteBuffer buffer = ByteBuffer.allocate(64);
			socketChannel.read(buffer);
			buffer.flip();
			while(buffer.hasRemaining()) {
				message.append((char) buffer.get());
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return message.toString();
	}

}
