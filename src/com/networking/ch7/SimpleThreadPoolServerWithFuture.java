package com.networking.ch7;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

// Future interface
public class SimpleThreadPoolServerWithFuture {

	public static void main(String[] args) {
		System.out.println("Thread pool server with future started");

		float price = 0f;
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		Future<Float> future1 = executor.submit(new Callable<Float>() {

			@Override
			public Float call() throws Exception {
				System.out.println("price1");
				Thread.sleep(1000);
				return 1f;
			}

		});

		Future<Float> future2 = executor.submit(new Callable<Float>() {

			@Override
			public Float call() throws Exception {
				System.out.println("price2");
				Thread.sleep(1000);
				return 2f;
			}

		});

		try {
			Float firstPart = future1.get();
			Float secondPart = future2.get();
			price = firstPart + secondPart;

			System.out.println("price=" + price);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		executor.shutdown();
		System.out.println("Thread pool server with future terminated");
	}

}
