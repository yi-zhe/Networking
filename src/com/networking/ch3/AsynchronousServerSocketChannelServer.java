package com.networking.ch3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsynchronousServerSocketChannelServer {
	
	public AsynchronousServerSocketChannelServer() {
		System.out.println("Asynchronous Server Started");
		try(AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open()) {
			InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5000);
			serverChannel.bind(hostAddress);
			System.out.println("Waiting for client to connect...");
			Future<AsynchronousSocketChannel> acceptResult = serverChannel.accept();
			AsynchronousSocketChannel clientChannel = (AsynchronousSocketChannel) acceptResult.get();
			System.out.println("Messages from client: ");
			while((clientChannel != null) && clientChannel.isOpen()) {
				ByteBuffer buffer = ByteBuffer.allocate(32);
				Future result = clientChannel.read(buffer);
				result.get();
				buffer.flip();
				String message = new String(buffer.array()).trim();
				System.out.println(message);
				if("quit".equalsIgnoreCase(message)) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new AsynchronousServerSocketChannelServer();
	}

}
