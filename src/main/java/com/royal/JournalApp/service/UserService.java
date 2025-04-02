package com.royal.JournalApp.service;

import com.royal.JournalApp.entity.JournalEntry;
import com.royal.JournalApp.entity.User;
import com.royal.JournalApp.repository.JournalEntryRepository;
import com.royal.JournalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createEntry(User user) {
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
