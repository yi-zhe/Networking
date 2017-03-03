package com.networking.ch1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) {
        try {
			InetAddress address = InetAddress.getByName("www.baidu.com");
		    System.out.println(address);
        } catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
