package com.networking.ch3;

import java.nio.channels.SocketChannel;

public class ClientHandler implements Runnable {

	private SocketChannel socketChannel;

    public ClientHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        System.out.println("Client Handler started for " + socketChannel);

        String partName;
        while(true) {
            partName = HelperMethods.receiveMessage(socketChannel);
            if("quit".equalsIgnoreCase(partName)) {
                break;
            } else {
                Float price = PartsServer.getPrice(partName);
                HelperMethods.sendMessage(socketChannel, String.valueOf(price));
            }
        }

        System.out.println("Client Handler terminated for " + socketChannel);
    }
}
