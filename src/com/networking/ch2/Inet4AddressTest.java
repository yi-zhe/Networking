package com.networking.ch2;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Inet4AddressTest {

	public static void main(String[] args) {
        // 0.0.0.0  当网络接口没有ip地址时, 使用此地址通过DHCP请求IP地址
		try {
			Inet4Address address = (Inet4Address) Inet4Address.getByName("0.0.0.0");
			System.out.println(address.isAnyLocalAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 127.0.0.1 lookback 环回地址
		try {
			Inet4Address address = (Inet4Address) Inet4Address.getByName("127.0.0.1");
			System.out.println(address.isLoopbackAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}

}
