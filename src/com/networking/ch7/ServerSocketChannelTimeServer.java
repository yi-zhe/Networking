package com.networking.ch7;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class ServerSocketChannelTimeServer {

	private static Selector selector;

	static class SelectorHandler implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					int readyChannels = selector.select(500);
					if (readyChannels != 0) {
						Set<SelectionKey> keys = selector.selectedKeys();
						Iterator<SelectionKey> keyIterator = keys.iterator();
						while (keyIterator.hasNext()) {
							SelectionKey key = keyIterator.next();
							if (key.isAcceptable()) {

							} else if (key.isConnectable()) {

							} else if (key.isReadable()) {

							} else if (key.isWritable()) {
								String message = "Date: " + new Date();
								ByteBuffer buffer = ByteBuffer.allocate(64);
								buffer.put(message.getBytes());
								buffer.flip();
								SocketChannel socketChannel = null;
								while (buffer.hasRemaining()) {
									socketChannel = (SocketChannel) key.channel();
									socketChannel.write(buffer);
								}
								System.out.println("Sent: " + message + " to: " + socketChannel);
							}
							Thread.sleep(1000);
							keyIterator.remove();
						}
					}
				} catch (IOException | InterruptedException e) {

				}
			}
		}

	}

	public static void main(String[] args) {
		System.out.println("Time server started");

		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(5000));

			selector = Selector.open();
			new Thread(new SelectorHandler()).start();

			while (true) {
				SocketChannel socketChannel = serverSocketChannel.accept();
				System.out.println("Socket channel accepted - " + socketChannel);

				if (socketChannel != null) {
					socketChannel.configureBlocking(false);
					selector.wakeup();
					socketChannel.register(selector, SelectionKey.OP_WRITE, null);
				}

			}

		} catch (ClosedChannelException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Time server terminated");
	}

}
