package com.spotifyapp.spotify.repository;

//Create a UserRepository interface which extends MongoRepository
//with User as the entity and String as the primary key
//Use @Repository annotation
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spotifyapp.spotify.model.User;

//import Optional class from java.util
import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

	// create a method to retrieve user based on emailId and password
	// which returns Optional<User>
	public Optional<User> findByEmailIdAndPassword(String emailId, String password);

	public Optional<User> findByEmailId(String emailId);

}
