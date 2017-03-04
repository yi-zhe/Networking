package com.networking.ch2;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Inet6AddressTest {

	public static void main(String[] args) {
        try {
			InetAddress addresses[] = InetAddress.getAllByName("www.google.com");
			for(InetAddress address : addresses) {
				if( (address instanceof Inet6Address) && ((Inet6Address)address).isIPv4CompatibleAddress() ) {
					System.out.println(address + " is ipv4 compitable");
				} else {
					System.out.println(address + " is not ipv4 compitable");
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
