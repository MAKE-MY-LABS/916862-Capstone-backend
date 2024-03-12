package com.spotifyapp.spotify.model;

//Create a user mongodb entity class with the following fields
//emailId,password,username,mobile and use lombok to generate the getters and setters, toString
//Use lombok to generate the constructor with all fields , no argus constructor
//Use  @Document to map the class to the collection in MongoDB
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {
	@Id
	private String emailId;
	private String userName;
	private String password;
	private String userId;
}
