package com.xdevnull.bot.script;

import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.json.Json;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * Youtube Search
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class YoutubeSearch {
	
	/**
	 * YouTube API Key
	 */
	private final String API_KEY;
	
	/**
	 * YouTube API Url
	 */
	private final String API_URL = "https://www.googleapis.com/youtube/v3/search/";
	
	/**
	 * YouTube LINK Prefix
	 */
	private final String LINK_PREFIX = "https://youtu.be/";
	
	/**
	 * YoutubeSearch
	 * @param API_KEY
	 */
	public YoutubeSearch(String API_KEY) {
		this.API_KEY = API_KEY;
	}
	
	/**
	 * Search a keyword in youtube and return array of link
	 * @param keyword
	 * @return
	 */
	public String[] search(String keyword) {
		try {
		
			//Query
			String query = "?part=id&key=" + API_KEY + "&type=video&order=relevance&maxResults=15&q=" + URLEncoder.encode(keyword, java.nio.charset.StandardCharsets.UTF_8.toString());
		
			//HTTP Client
			HttpClient httpClient = HttpClientBuilder.create().build();
			
			//Create HttpGet
			HttpGet httpGet = new HttpGet(API_URL + query);
			
			//Execute Request
	        HttpResponse response = httpClient.execute(httpGet);
	        
			//HTTP Response Content
			StringBuilder responseBody = new StringBuilder(EntityUtils.toString(response.getEntity()));
			
			//Read and Parse JSON
			JsonReader jsonReader = Json.createReader(new StringReader(responseBody.toString()));
			
			//Read
			JsonObject object = jsonReader.readObject();
			jsonReader.close();
			
			//All items
			JsonArray items = object.getJsonArray("items");
			ArrayList<String> result = new ArrayList<String>();
			for(int i = 0; i < items.size(); i++) 
				result.add(LINK_PREFIX + items.getValuesAs(JsonObject.class).get(i).getJsonObject("id").getString("videoId"));
			return result.toArray(new String[result.size()]);
		}
		catch(Exception e) {
			System.out.println("Unable to search in youtube!");
		}
		return new String[]{};
	}
	
}
