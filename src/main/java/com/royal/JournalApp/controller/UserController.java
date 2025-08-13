package com.royal.JournalApp.controller;

import com.royal.JournalApp.entity.JournalEntry;
import com.royal.JournalApp.entity.User;
import com.royal.JournalApp.service.JournalEntryService;
import com.royal.JournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The UserController class provides RESTful APIs for managing users.
 * It handles HTTP requests for fetching all users, creating a new user, and updating user details.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createEntry(user);
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username){
        User userInDb = userService.findByUserName(username);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.createEntry(userInDb);

        }
        return new ResponseEntity<>(userInDb,HttpStatus.NO_CONTENT);
    }

}
