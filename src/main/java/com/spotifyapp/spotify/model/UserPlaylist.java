package com.spotifyapp.spotify.model;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Create a model class to store the spotifyuser id and token and playlist id
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userplaylist")
public class UserPlaylist {

	@Id
	private String userId;
	// Variable to store the token
	private String token;
	
	
	
	private JSONObject spotifyUserResponse;

}
