package com.networking.ch3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ChatClient {
	
	public ChatClient() {
		SocketAddress address = new InetSocketAddress("127.0.0.1", 5000);
		try(SocketChannel socketChannel = SocketChannel.open(address)) {
            System.out.println("Connected to Chat Server");
            String message;
            Scanner scanner = new Scanner(System.in);
            while(true) {
            	System.out.println("Waiting for message from the server...");
            	System.out.println("Message: "+HelperMethods.receiveFixedLengthMessage(socketChannel));
            	System.out.println("> ");
            	message = scanner.nextLine();
            	if("quit".equalsIgnoreCase(message)) {
            		HelperMethods.sendFixedLengthMessage(socketChannel, "Client terminating");
            		break;
            	}
        		HelperMethods.sendFixedLengthMessage(socketChannel, message);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
        new ChatClient();
	}

}
