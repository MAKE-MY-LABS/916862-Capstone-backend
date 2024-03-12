package com.spotifyapp.spotify.service;

import com.spotifyapp.spotify.exceptions.UserAlreadyExistException;
import com.spotifyapp.spotify.exceptions.UserNotFoundException;
import com.spotifyapp.spotify.model.User;
import com.spotifyapp.spotify.model.UserPlaylist;

public interface SpotifyService {
	
	//Abstract method to save the user
	public User saveUser(User user) throws UserAlreadyExistException;
	//Abstract method to find the user by emailId and password throws the UserNotFoundException
	public User findByEmailIdAndPassword(String emailId, String password) throws UserNotFoundException;
	
	//Abstract method to get user profile and throws the UserNotFoundException
	public String getUserProfile(String token) throws UserNotFoundException;
	
	//Abstract Method to add a playlist to the user profile
	public String addPlaylist(String token,UserPlaylist userPlaylist) throws UserNotFoundException;
	
	//Abstract method to get the top tracks of the user
	public String getTopTracks(String token) throws UserNotFoundException;
	
	//Abstract method to get the user playlist
	public String getUserPlaylist(String token) throws UserNotFoundException;
	
	

}
