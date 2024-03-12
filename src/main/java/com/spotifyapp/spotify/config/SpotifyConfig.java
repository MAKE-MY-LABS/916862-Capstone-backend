package com.spotifyapp.spotify.config;

/*
 * Create a spotify config class to create RestTemplate bean and import the Spotifyapiurl and bearer token from application.properties
 *
 * */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Configuration
@Data
public class SpotifyConfig {
	
	@Value("${bearer.token}")
	private String bearerToken;
	
	//Configure the spotify web api and profile url
	@Value("${spotifyapi.profile.url}")
	private String spotifyProfileUrl;
	@Value("${spotifyapi.webapi.url}")
	private String spotifyWebApiUrl;
	//Configure the spotifyapi.user.toptracks.url
	@Value("${spotifyapi.user.toptracks.url}")
	private String spotifyUserTopTracksUrl;

	@Value("${spotifyapi.user.playlisturl}")
	private String spotifyUserPlaylistUrl;
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	

	

}

