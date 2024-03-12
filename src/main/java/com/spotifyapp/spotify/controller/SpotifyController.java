package com.spotifyapp.spotify.controller;

/**
 * Create a SpotifyController class to implement the SpotifyController interface
 * and autowired the SpotifyService component
 * 
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spotifyapp.spotify.exceptions.UserAlreadyExistException;
import com.spotifyapp.spotify.exceptions.UserNotFoundException;
import com.spotifyapp.spotify.model.LoginResponse;
import com.spotifyapp.spotify.model.User;
import com.spotifyapp.spotify.model.UserPlaylist;
import com.spotifyapp.spotify.repository.UserRepository;
import com.spotifyapp.spotify.service.SpotifyService;
import com.spotifyapp.spotify.util.JwtUtils;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class SpotifyController {

	@Autowired
	private SpotifyService spotifyService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtils jwtUtils;

	//Create a method to register the user which accepts the user object 
	//and returns the responseEntity of the user object
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) throws UserAlreadyExistException {
		User savedUser = spotifyService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	//Create a method to login the user which accepts the user object
	//and returns the responseEntity of the user object
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@RequestBody User user) throws UserNotFoundException {
		User userObj = spotifyService.findByEmailIdAndPassword(user.getEmailId(), user.getPassword());
		String token = jwtUtils.generateToken(userObj.getEmailId());
		LoginResponse loginResponse = LoginResponse.builder().jwtToken(token).status("Success").build();
		return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
	}
	
	//Create a method to get the user profile which accepts the token
	//and returns the responseEntity of the user object
	@PostMapping("/profile")
	public ResponseEntity<String> getUserProfile(@RequestBody String token) throws UserNotFoundException {
		String response = spotifyService.getUserProfile(token);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	//Create a method to add the playlist to the user profile which accepts the token, spotifyUserId and userPlaylist object
	//and returns the responseEntity of the user object
	@PostMapping("/addplaylist")
	public ResponseEntity<String> addPlaylist(@RequestBody UserPlaylist userPlaylist) throws UserNotFoundException {
		String spotifyResponseObj = spotifyService.addPlaylist(userPlaylist.getToken(),userPlaylist);
		return ResponseEntity.status(HttpStatus.OK).body(spotifyResponseObj);
	}
	
	//Create a method to get the top tracks of the user which accepts the token
	//and returns the responseEntity of the user object
	@PostMapping("/toptracks")
	public ResponseEntity<String> getTopTracks(@RequestBody String token) throws UserNotFoundException {
		String response = spotifyService.getTopTracks(token);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	//Create a method to get the user playlist which accepts the token
	//and returns the responseEntity of the user object
	@PostMapping("/userplaylist")
	public ResponseEntity<String> getUserPlaylist(@RequestBody String token) throws UserNotFoundException {
		String response = spotifyService.getUserPlaylist(token);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
