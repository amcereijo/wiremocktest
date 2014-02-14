package com.amcereijo.wiremocktest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpPostCall {

	public static final int PORT = 9080;
	public static final String HOST = "http://localhost:";
	public static final String URL = "/hello/hello.json";

	public String post() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try {
			HttpGet getRequest = new HttpGet(HOST+PORT+URL);
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			StringBuffer out = new StringBuffer();
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				out.append(output);
			}

			httpClient.getConnectionManager().shutdown();
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
