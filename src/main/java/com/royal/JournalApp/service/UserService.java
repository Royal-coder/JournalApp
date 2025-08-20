package com.royal.JournalApp.service;

import com.royal.JournalApp.entity.JournalEntry;
import com.royal.JournalApp.entity.User;
import com.royal.JournalApp.repository.JournalEntryRepository;
import com.royal.JournalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing User entities.
 *
 * This class provides functionality for creating, retrieving, deleting,
 * and searching for User entities within the application. It acts as an
 * intermediary between the controller layer and the data persistence
 * components.
 *
 * Methods:
 * - `createEntry(User user)`: Persists a new User entity in the database.
 * - `getAll()`: Retrieves all User entities from the database.
 * - `getEntryById(ObjectId myid)`: Fetches a User entity by its unique identifier.
 * - `deleteEntryById(ObjectId myid)`: Deletes a User entity based on its unique identifier.
 * - `findByUserName(String username)`: Retrieves a User entity by its username.
 *
 * Dependencies:
 * - `UserRepository`: Directs interactions with the MongoDB data source.
 *
 * Notes:
 * - The class is annotated with `@Service` and `@Component` to identify it
 *   as a Spring-managed service component for dependency injection within
 *   the application.
 */
@Service
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void createEntry(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        userRepository.save(user);

    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getEntryById(ObjectId myid){
        return userRepository.findById(myid);
    }

    public void deleteEntryById(ObjectId myid){
        userRepository.deleteById(myid);
    }

    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }

}
