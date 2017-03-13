package com.networking.ch4;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable {
	
	private final Socket socket;

	public ClientHandler(Socket remote) {
	        socket = remote;
	}

	@Override
	public void run() {
        System.out.println("\nClientHandler started for " + socket);
        handleRequest(socket);
        System.out.println("ClientHandler terminated for " + socket + "\n");
	}

	private void handleRequest(Socket socket) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())))	 {
        	String headerLine = reader.readLine();
        	StringTokenizer tokenizer = new StringTokenizer(headerLine);
        	String httpMethod = tokenizer.nextToken();
        	
        	if("GET".equalsIgnoreCase(httpMethod)) {
        		System.out.println("Get method processed");
        		String httpQueryString = tokenizer.nextToken();
        		StringBuilder builder = new StringBuilder();
        		builder.append("<html><h1>WebServer Home Page...</h1><br>")
        		       .append("<b>Welcome to my web server!</b><br>")
        		       .append("</html>");
        		sendResponse(socket, 200, builder.toString());
        	} else {
        		System.out.println("The Http method is not recongnized!");
        		sendResponse(socket, 405, "Method is not allowed");
        	}
        } catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendResponse(Socket socket, int statusCode, String string) {
       String statusLine;
       String serverHeader = "Server: WebServer\r\n";
       String contentTypeHeader = "Content-Type: text/html\r\n";
       try(DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
    	   
    	   if(200 == statusCode) {
    		   statusLine = "HTTP/1.0 200 OK\r\n";
    		   String contentLengthHeader = "Content-Length: " + string.length();
    		   out.writeBytes(statusLine);
    		   out.writeBytes(serverHeader);
    		   out.writeBytes(contentTypeHeader);
    		   out.writeBytes("\r\n");
    		   out.writeBytes(string);
    	   } else if(405 == statusCode) {
    		   statusLine = "HTTP/1.0 405 Method Not Allowed\r\n";
    		   out.writeBytes(statusLine);
    		   out.writeBytes("\r\n");
    	   } else {
    		   statusLine = "HTTP/1.0 404 Not Found\r\n";
    		   out.writeBytes(statusLine);
    		   out.writeBytes("\r\n");
    	   }
    	   
    	   out.close();
       } catch (IOException e) {
		e.printStackTrace();
	}
	}

}
