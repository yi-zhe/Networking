package com.networking.ch3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsynchronousSocketChannelClient {

	public static void main(String[] args) {
        try(AsynchronousSocketChannel client = AsynchronousSocketChannel.open()) {
        	InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5000);
        	Future future = client.connect(hostAddress);
        	future.get();
        	
        	System.out.println("Client is started: " + client.isOpen());
        	System.out.println("Sending messages to server: ");
        	Scanner scanner = new Scanner(System.in);
        	String message;
        	while(true) {
        		System.out.println("> ");
        		message = scanner.nextLine();
        		ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        		Future result = client.write(buffer);
        		while(!result.isDone()) {
            		System.out.print("1");
        		}
        		
        		if("quit".equalsIgnoreCase(message)) {
        			break;
        		}
        	}
        } catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
