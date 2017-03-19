package com.networking.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.NumberFormat;

public class WorkerThread2 implements Runnable {

	private Socket clientSocket;

	public WorkerThread2(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		System.out.println("Worker Thread started");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
			PrintStream out = new PrintStream(clientSocket.getOutputStream());
			String partName = reader.readLine();
			float price = 0f;
			try {
				price = new WorkerCallable(partName).call();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
