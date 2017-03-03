package com.networking.ch1;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class URLConnectionWithChannelTest {

	public static void main(String[] args) {
		ReadableByteChannel channel = null;
		try {
			URL url = new URL("http://www.baidu.com");
			URLConnection urlConnection = url.openConnection();
			InputStream inputStream = urlConnection.getInputStream();
			channel = Channels.newChannel(inputStream);
			ByteBuffer buffer = ByteBuffer.allocate(102400);
			while (channel.read(buffer) > 0) {
				System.out.println(new String(buffer.array()));
				buffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (channel != null) {
				try {
					channel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
