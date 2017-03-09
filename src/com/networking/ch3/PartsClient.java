package com.networking.ch3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class PartsClient {

	public PartsClient() {
        System.out.println("Parts Client started");
        SocketAddress address = new InetSocketAddress("127.0.0.1", 5000);
        try(SocketChannel socketChannel = SocketChannel.open(address)) {
            System.out.println("Connected to parts server");
            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.println("Enter part name");
                String partName = scanner.nextLine();

                if("quit".equalsIgnoreCase(partName)) {
                    break;
                } else {
                    HelperMethods.sendMessage(socketChannel, partName);
                    System.out.println("The price is" + HelperMethods.receiveMessage(socketChannel));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		new PartsClient();
	}

}
