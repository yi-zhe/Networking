package com.networking.ch1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleEchoServer {

	public static void main(String[] args) {
        System.out.println("Simple Echo Server");
        try (ServerSocket serverSocket = new ServerSocket(6000)) {
        	System.out.println("Waiting for connection...");
        	Socket clientSocket = serverSocket.accept();
        	System.out.println("Connected to client");

        	BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String input;
            while((input = reader.readLine()) != null) {
            	System.out.println("Server: "+ input);
            	writer.println(input);
            }
        } catch(IOException e) {
        	e.printStackTrace();
        }
	}
}
