package com.networking.ch2;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

public class NetworkInterfaces {

	public static void main(String[] args) {
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			System.out.println("Name        Display name        MAC\n");

			for (NetworkInterface element : Collections.list(interfaces)) {
				byte[] mac = element.getHardwareAddress();
				StringBuilder builder = new StringBuilder();
				if (mac != null) {
					for (int i = 0; i < mac.length; i++) {
						builder.append(String.format("%02X%s", mac[i], i < mac.length - 1 ? "-" : ""));
					}
				} else {
					builder.append("---");
				}
				System.out.printf("%-12s %-12s %-12s\n", element.getName(), element.getDisplayName(),
						builder.toString());
			}
		} catch (Exception e) {

		}
	}

}
