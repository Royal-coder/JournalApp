package com.royal.JournalApp.repository;

import com.royal.JournalApp.entity.JournalEntry;
import com.royal.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String username);
}
