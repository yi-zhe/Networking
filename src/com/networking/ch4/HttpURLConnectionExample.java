package com.networking.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionExample {

	public static void main(String[] args) throws Exception {
		HttpURLConnectionExample http = new HttpURLConnectionExample();

		http.sendGet();
	}

	private void sendGet() throws Exception {
		String query = "http://www.baidu.com/";
		URL url = new URL(query);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = connection.getResponseCode();
		if (200 == responseCode) {
			String response = getResponse(connection);
			System.out.println("response: " + response.toString());
		} else {
			System.out.println("Bad Response Code: " + responseCode);
		}
	}

	private String getResponse(HttpURLConnection connection) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			StringBuilder builder = new StringBuilder();
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				builder.append(inputLine);
			}
			reader.close();
			return builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
