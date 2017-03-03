package com.networking.ch1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SimpleEchoClient {

	public static void main(String[] args) {
        System.out.println("Simple Echo Client");
        
        Socket clientSocket = null;
        Scanner scanner = null;
        try {
        	System.out.println("Waiting for connection...");
            InetAddress address = InetAddress.getLocalHost();
            clientSocket = new Socket(address, 6000);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        	System.out.println("Connected to server");
            scanner = new Scanner(System.in);
        	while(true) {
            	System.out.println("Enter text: ");
            	String input = scanner.nextLine();
            	if("quit".equals(input)) {
            		break;
            	}
            	
            	out.println(input);
            	String response = reader.readLine();
            	System.out.println("Server response: " + response);
        	}
        } catch (IOException e) {
        	
        } finally {
        	if(clientSocket != null) {
        		try {
					clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	
        	if(scanner != null) {
        		scanner.close();
        	}
        }
	}

}
