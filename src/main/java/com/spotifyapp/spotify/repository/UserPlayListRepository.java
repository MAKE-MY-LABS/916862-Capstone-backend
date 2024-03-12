package com.spotifyapp.spotify.repository;

//Create a user playlist repository interface to perform the crud operations on the userplaylist collection extends the MongoRepository
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spotifyapp.spotify.model.UserPlaylist;

@Repository
public interface UserPlayListRepository extends MongoRepository<UserPlaylist, String> {

}

