package com.networking.ch1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedEchoServer implements Runnable {

	private static Socket clientSocket;
	private static ServerSocket serverSocket;

	public ThreadedEchoServer(Socket clientSocket) {
		ThreadedEchoServer.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		System.out.println("Connected to client using [" + Thread.currentThread() + "]");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
			String input;

			while ((input = reader.readLine()) != null) {
				System.out.println("CLient request [" + Thread.currentThread() + "] " + input);
				out.println(input);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("Threaded Echo Server");

		try {
			serverSocket = new ServerSocket(6000);
			while (true) {
				System.out.println("Waiting for connection...");

				clientSocket = serverSocket.accept();
				ThreadedEchoServer tes = new ThreadedEchoServer(clientSocket);
				new Thread(tes).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Threaded Echo Server terminating");
	}
}
