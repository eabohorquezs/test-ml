package com.mercadolibre.ipmonitor.esl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class BasicExternalServiceConnector {

	public String getExternalServiceResponseSecured(String urlString) throws IOException {
		URL url = new URL(urlString);
        
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("getExternalServiceResponseSecured error : HTTP Error code : "
                    + conn.getResponseCode());
        }
        
        String line;
        StringBuilder sb = new StringBuilder();
        try (
        		InputStreamReader in = new InputStreamReader(conn.getInputStream());
        		BufferedReader br = new BufferedReader(in);
        	) {
        	while((line = br.readLine()) != null) {
        		sb.append(line + System.lineSeparator());
        	}
        	
        } finally {
        	conn.disconnect();
        }
        
        return sb.toString();
	}
	
	public String getExternalServiceResponse(String urlString) throws IOException {
		URL url = new URL(urlString);
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("getExternalServiceResponse error : HTTP Error code : "
                    + conn.getResponseCode());
        }
        
        String line;
        StringBuilder sb = new StringBuilder();
        try (
        		InputStreamReader in = new InputStreamReader(conn.getInputStream());
        		BufferedReader br = new BufferedReader(in);
        	) {
        	while((line = br.readLine()) != null) {
        		sb.append(line + System.lineSeparator());
        	}
        	
        } finally {
        	conn.disconnect();
        }
        
        return sb.toString();
	}

}