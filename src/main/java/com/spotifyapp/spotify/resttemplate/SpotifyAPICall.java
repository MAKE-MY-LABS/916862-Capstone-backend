package com.spotifyapp.spotify.resttemplate;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
/* 
 * Create a Component class to call Spotify API using RestTemplate by autowiring the RestTemplate bean and spotifyapiconfig class
 * */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.spotifyapp.spotify.model.UserPlaylist;

@Component
public class SpotifyAPICall {
	@Autowired
	private RestTemplate restTemplate;
	

	//Create a method with restTemplate exchange method to call Spotify API
	/**
	 * This method is used to call Spotify API using RestTemplate exchange method
	 * 
	 * @return String
	 */
	public String getSpotifyData(String url, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
		return response;
	}
	
	
	//Create a method with restTemplate exchange method with HTTP Post call to call Spotify API
	/**
	 * This method is used to call Spotify API using RestTemplate exchange method
	 * with HTTP Post call
	 * 
	 * @return String
	 */
	public String postSpotifyData(String url, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		Map<String, String> userPlaylistObj = new HashMap<>();
		userPlaylistObj.put("name", "Capestone Project Implementation");
		userPlaylistObj.put("description", "New playlist description");
		userPlaylistObj.put("public", "false");
		HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(userPlaylistObj,headers);	
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();
	}
	
	

	
	
}
