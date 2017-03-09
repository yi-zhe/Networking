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
	
	public static void sendMessage(SocketChannel socketChannel, String message) {
		try {
			ByteBuffer buffer = ByteBuffer.allocate(message.length() + 1);
			buffer.put(message.getBytes());
			buffer.put((byte)(0x00));
			buffer.flip();
			while(buffer.hasRemaining()) {
				socketChannel.write(buffer);
			}
			System.out.println("Sent: " + message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String receiveMessage(SocketChannel socketChannel) {
		try {
			ByteBuffer buffer = ByteBuffer.allocate(16);
			StringBuilder message = new StringBuilder();
			while(socketChannel.read(buffer) > 0) {
				char byteRead = 0x00;
				buffer.flip();
				while(buffer.hasRemaining()) {
					byteRead = (char) buffer.get();
					if(byteRead == 0x00) {
						break;
					}
					message.append(byteRead);
				}
				
				if(byteRead == 0x00) {
					break;
				}
				
				return message.toString();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "";
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
