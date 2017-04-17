package com.sn.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SourceRequest {
	
	public static JSONArray getAllSources()
	{
		URL url = null;
		StringBuilder sb = new StringBuilder();
		HttpURLConnection connection = null;
		int responseCode = 0;
		BufferedReader bufferedReader = null;
		
		sb.append(APIConstants.BASE_URL);
		sb.append(APIConstants.VERSION);
		sb.append("/");
		sb.append(APIConstants.SOURCES);
		sb.append("?");
		sb.append(APIConstants.LANGUAGE);
		sb.append("=");
		sb.append(APIConstants.LANG_ENGLISH);
		
		try {
			url = new URL(sb.toString());
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			responseCode = connection.getResponseCode();
			
			if(responseCode == HttpURLConnection.HTTP_OK) {
				bufferedReader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				sb = new StringBuilder();
				String line = null;
				while((line = bufferedReader.readLine()) != null) {
					sb.append(line);
				}
				
				JSONObject jsonObject = new JSONObject(sb.toString());
				JSONArray jsonArray = jsonObject.getJSONArray(APIConstants.SOURCES);
				return jsonArray;
			}
		} catch (MalformedURLException e) {
			Logger.getLogger(SourceRequest.class.getName()).log(Level.SEVERE, null, e);
			return null;
		} catch (IOException e) {
			Logger.getLogger(SourceRequest.class.getName()).log(Level.SEVERE, null, e);
			return null;
		} catch (JSONException e) {
			Logger.getLogger(SourceRequest.class.getName()).log(Level.SEVERE, null, e);
			return null;
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					Logger.getLogger(SourceRequest.class.getName()).log(Level.SEVERE, null, e);
					bufferedReader = null;
					return null;
				}
			}
		}
		return null;
	}
}
