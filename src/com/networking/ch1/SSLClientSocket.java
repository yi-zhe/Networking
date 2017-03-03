package com.networking.ch1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.net.ssl.SSLSocketFactory;

public class SSLClientSocket {

	public static void main(String[] args) {
        System.out.println("SSLClientSocket Started");
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try (Socket socket = sf.createSocket("localhost", 8000)) {
        	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        	BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        	Scanner scanner = new Scanner(System.in);
        	
        	while(true) {
        		System.out.println("Enter text:");
        		String input = scanner.nextLine();
            	if("quit".equals(input)) {
            		break;
            	}
            	
            	out.println(input);
            	String response = reader.readLine();
            	System.out.println("Server response: " + response);
        	}
        } catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
