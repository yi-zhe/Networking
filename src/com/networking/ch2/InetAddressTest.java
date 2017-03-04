package com.networking.ch2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) {
        try {
			InetAddress names[] = InetAddress.getAllByName("www.baidu.com");
			for(InetAddress element : names) {
				System.out.println(element);
				// Fully Qualified Domain Name
				System.out.println("CanonicalHostName:" + element.getCanonicalHostName());
				System.out.println("HostName:" + element.getHostName());
				System.out.println("HostAddress:" + element.getHostAddress());
				// always false, and don't know why
				System.out.println("Is reachable:" + element.isReachable(5000));
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
