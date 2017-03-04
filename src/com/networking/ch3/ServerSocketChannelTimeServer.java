package com.networking.ch3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class ServerSocketChannelTimeServer {

	public static void main(String[] args) {
        System.out.println("Time Server started");
        try {
        	ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        	serverSocketChannel.socket().bind(new InetSocketAddress(5000));
        	
        	while(true) {
        		System.out.println("Waiting for request...");
        		SocketChannel socketChannel = serverSocketChannel.accept();
        		
        		if(null != socketChannel) {
        			String message = "Date: " + new Date(System.currentTimeMillis());
        			ByteBuffer buffer = ByteBuffer.allocate(64);
        			buffer.put(message.getBytes());
        			buffer.flip();
        			while(buffer.hasRemaining()) {
        				socketChannel.write(buffer);
        			}
        			System.out.println("Sent: " + message);
        		}
        	}
        } catch(IOException e) {
        	e.printStackTrace();
        }
	}

}
