package com.networking.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleMutiThreadedServer implements Runnable {

	private static ConcurrentHashMap<String, Float> map;
	private Socket clientSocket;

	static {
		map = new ConcurrentHashMap<>();
		map.put("Axle", 238.50f);
		map.put("Gear", 45.55f);
		map.put("Wheel", 86.30f);
		map.put("Rotor", 8.50f);
	}

	public SimpleMutiThreadedServer(Socket socket) {
		this.clientSocket = socket;
	}

	public static void main(String[] args) {
		System.out.println("Multi-Threaded Server started");

		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			while (true) {
				System.out.println("Listening for a client connection");
				Socket socket = serverSocket.accept();
				System.out.println("Connected to a Client");
				new Thread(new SimpleMutiThreadedServer(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Multi-Threaded Server terminated");
	}

	@Override
	public void run() {
		System.out.println("Client Thread Started");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
			PrintStream out = new PrintStream(clientSocket.getOutputStream());
			String partName = reader.readLine();
			float price = map.get(partName);
			out.println(price);
			NumberFormat format = NumberFormat.getCurrencyInstance();
			System.out.println("Request for " + partName + " and returned a price of " + format.format(price));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
