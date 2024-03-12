package com.spotifyapp.spotify.service;

import java.util.Optional;

import org.json.JSONObject;
/*
 * Create a SpotifyService class to implement the SpotifyService interface and autowired the spotifyapicall component c
 * 
 */
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spotifyapp.spotify.config.SpotifyConfig;
import com.spotifyapp.spotify.exceptions.UserAlreadyExistException;
import com.spotifyapp.spotify.exceptions.UserNotFoundException;
import com.spotifyapp.spotify.model.User;
import com.spotifyapp.spotify.model.UserPlaylist;
import com.spotifyapp.spotify.repository.UserPlayListRepository;
import com.spotifyapp.spotify.repository.UserRepository;
import com.spotifyapp.spotify.resttemplate.SpotifyAPICall;
 
@Service
public class SpotifyServiceImpl implements SpotifyService {
		@Autowired
		private SpotifyAPICall spotifyAPICall;
		
		@Autowired
		private SpotifyConfig spotifyConfig;
		
		//Autowired the user repository
		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private UserPlayListRepository userPlayListRepository;

		//Overrida a method to save the user which accepts the user object and throws userAlreadyExistException
		@Override
		public User saveUser(User user) throws UserAlreadyExistException {
			//Create a user object using optional interface and find the user by emailId
			Optional<User> userOptional = userRepository.findByEmailId(user.getEmailId());
			if (userOptional.isPresent()) {
				throw new UserAlreadyExistException("User already exist");
			}
			return userRepository.save(user);
		}

		//Override a method to find the user by emailId and password and throws the userNotFoundException
		@Override
		public User findByEmailIdAndPassword(String emailId, String password) throws UserNotFoundException{
			// Create a user object using optional interface and find the user by emailId
			// and password
			Optional<User> userOptional = userRepository.findByEmailIdAndPassword(emailId, password);
			if (userOptional.isPresent()) {
				return userOptional.get();
			}
			return null;
		}
		
		//Override a method to get user profile using spotify API and throws the userNotFoundException
		@Override
		public String getUserProfile(String token) throws UserNotFoundException {
			String url = spotifyConfig.getSpotifyWebApiUrl() + spotifyConfig.getSpotifyProfileUrl();
			String response = spotifyAPICall.getSpotifyData(url, "Bearer "+token);
			if (response == null) {
				throw new UserNotFoundException("User not found");
			}
			return response;
		}
		
		//Override a method to add a playlist to the user profile using spotify API and throws the userNotFoundException
		@Override
		public String addPlaylist(String token,UserPlaylist userPlaylist) throws UserNotFoundException {
			String url = spotifyConfig.getSpotifyWebApiUrl() + spotifyConfig.getSpotifyUserPlaylistUrl();
			String response = spotifyAPICall.postSpotifyData(url, token);
			userPlaylist.setSpotifyUserResponse(new JSONObject(response));
			userPlayListRepository.save(userPlaylist);
			if (response == null) {
				throw new UserNotFoundException("User not found");
			}
			return response;
		}
		
		//Override a method to get the top tracks of the user using spotify API and throws the userNotFoundException
		@Override
		public String getTopTracks(String token) throws UserNotFoundException {
			String url = spotifyConfig.getSpotifyWebApiUrl() + spotifyConfig.getSpotifyUserTopTracksUrl();
			String response = spotifyAPICall.getSpotifyData(url,  token);
			if (response == null) {
				throw new UserNotFoundException("User not found");
			}
			return response;
		}
		
		//Override a method to get the user playlist using spotify API and throws the userNotFoundException
		@Override
		public String getUserPlaylist(String token) throws UserNotFoundException {
			String url = spotifyConfig.getSpotifyWebApiUrl() + spotifyConfig.getSpotifyUserPlaylistUrl();
			String response = spotifyAPICall.getSpotifyData(url, token);
			if (response == null) {
				throw new UserNotFoundException("User not found");
			}
			return response;
		}

	}
