package com.spotifyapp.spotify.exceptions;

//Create a UserAlreadyExistException class which extends Exception
public class UserAlreadyExistException extends Exception {
	// Create a constructor with a message
	public UserAlreadyExistException(String message) {
		super(message);
	}
}
