package com.networking.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.concurrent.ConcurrentHashMap;

public class WorkerThread implements Runnable {

	private static ConcurrentHashMap<String, Float> map;
	private Socket clientSocket;

	static {
		map = new ConcurrentHashMap<>();
		map.put("Axle", 238.50f);
		map.put("Gear", 45.55f);
		map.put("Wheel", 86.30f);
		map.put("Rotor", 8.50f);
	}

	public WorkerThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		System.out.println("Worker Thread started");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
			PrintStream out = new PrintStream(clientSocket.getOutputStream());
			String partName = reader.readLine();
			float price = map.get(partName);
			out.println(price);
			NumberFormat format = NumberFormat.getCurrencyInstance();
			System.out.println("Request for " + partName + " and returned a price of " + format.format(price));
			clientSocket.close();
			System.out.println("Client Connection Terminated");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Worker Thread terminated");
	}

}
