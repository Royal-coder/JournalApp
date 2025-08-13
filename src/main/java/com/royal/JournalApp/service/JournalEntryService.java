package com.royal.JournalApp.service;

import com.royal.JournalApp.entity.JournalEntry;
import com.royal.JournalApp.entity.User;
import com.royal.JournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing JournalEntry entities.
 *
 * This class provides functionalities for creating, retrieving, updating,
 * and deleting journal entries. It acts as an intermediary between the
 * controller layer and the data persistence layer for journal entries.
 *
 * Key responsibilities:
 * - Saving a new journal entry to the database.
 * - Retrieving all journal entries.
 * - Fetching a journal entry by its unique identifier.
 * - Deleting a journal entry by its unique identifier.
 * - Updating an existing journal entry.
 *
 * Dependencies:
 * - JournalEntryRepository: Enables interaction with the persistent storage
 *   layer for journal entries.
 *
 * Annotations:
 * - @Service: Denotes this as a service component in Spring.
 * - @Component: Indicates that it is a Spring-managed component eligible for
 *   dependency injection.
 *
 * Methods:
 * - createEntry(JournalEntry journalEntry): Persists a given journal entry.
 * - getAll(): Fetches all journal entries from the database.
 * - getEntryById(ObjectId myid): Retrieves a journal entry by its unique identifier.
 * - deleteEntryById(ObjectId myid): Removes a journal entry by its unique identifier.
 * - updateEntry(ObjectId id, JournalEntry journalEntry): Updates an existing
 *   journal entry in the database.
 */
@Service
@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    public void createEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntryList().add(saved);
        userService.createEntry(user);

    }

    public void createEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);

    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId myid){
        return journalEntryRepository.findById(myid);
    }

    public void deleteEntryById(ObjectId myid, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntryList().removeIf(x -> x.getId().equals(myid));
        userService.createEntry(user);
        journalEntryRepository.deleteById(myid);
    }

    public void updateEntry(ObjectId id,JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
}
