package com.networking.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class HttpClient {

	public HttpClient() {
		System.out.println("Http Client Started");
		try {
			InetAddress address = InetAddress.getByName("127.0.0.1");
			Socket connection = new Socket(address, 8000);

			OutputStream out = connection.getOutputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			sendGet(out);
			System.out.println(getResponse(in));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getResponse(BufferedReader in) throws IOException {
		String inputLine;
		StringBuilder response = new StringBuilder();
		while((inputLine = in.readLine()) != null) {
			response.append(inputLine).append("\n");
		}
		return response.toString();
	}

	private void sendGet(OutputStream out) throws IOException {
		out.write("GET /default\r\n".getBytes());
		out.write("User-Agent: Mozilla/5.0\r\n".getBytes());
	}

	public static void main(String[] args) {
		new HttpClient();
	}

}
