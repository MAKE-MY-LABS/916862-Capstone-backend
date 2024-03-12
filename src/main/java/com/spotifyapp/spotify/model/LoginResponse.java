package com.spotifyapp.spotify.model;

import lombok.Builder;
//Create a model class with jwt token and status variables with @data annotation
import lombok.Data;

@Data
@Builder
public class LoginResponse {
	private String jwtToken;
	private String status;
}
