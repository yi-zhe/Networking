package com.networking.ch2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {

	public static void main(String[] args) {
        try {
			URL url = new URL("http://www.packpub.com");
			System.out.printf("Protocol: %-32s Host: %-32s\n", url.getProtocol(), url.getHost());
			System.out.printf("Port:%-32d Path:%-32s\n", url.getPort(), url.getPath());
			System.out.printf("Reference: %-32s File:%-32s\n", url.getRef(), url.getFile());
			System.out.printf("Authority:%-32s Query:%-32s\n", url.getAuthority(), url.getQuery());
			System.out.printf("User info:" + url.getUserInfo());
			System.out.println("getContent:" + url.getContent());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
