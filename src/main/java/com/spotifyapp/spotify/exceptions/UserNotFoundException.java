package com.spotifyapp.spotify.exceptions;

//Create a UserNotFoundException class which extends the exception class
public class UserNotFoundException extends Exception {
	// Create a constructor with a message
	public UserNotFoundException(String message) {
		super(message);
	}
}

