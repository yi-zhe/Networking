package com.networking.ch7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

// 7.1
// 7.2
public class SimpleThreadPoolServer {

	public static void main(String[] args) {
		System.out.println("Thread pool server started");

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			while (true) {
				System.out.println("Listening for a client connection");
				Socket socket = serverSocket.accept();
				System.out.println("Connected to a client");
				// WorkerThread task = new WorkerThread(socket);
				WorkerThread2 task = new WorkerThread2(socket);
				System.out.println("Task created: " + task);
				executor.execute(task);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		executor.shutdown();
		System.out.println("Thread pool server terminated");
	}

}
