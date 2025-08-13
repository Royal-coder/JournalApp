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
 * This controller class handles CRUD operations for the JournalEntry resource.
 * It provides endpoints for creating, retrieving, updating, and deleting journal entries
 * within the application.
 *
 * Annotations:
 * - @RestController: Indicates that this class handles RESTful web service requests.
 * - @RequestMapping: Maps requests at the class level to the "/journal" URL path.
 *
 * Dependencies:
 * - JournalEntryService: Acts as the service layer for performing business logic and
 *   data operations related to JournalEntry entities.
 *
 * Endpoints:
 * - POST /journal: Creates a new journal entry. The request body must contain the
 *   journal entry details. The created entry will also store the current date as its
 *   creation date.
 * - GET /journal/all: Fetches a list of all journal entries. Returns a 200 OK status if
 *   entries exist, or a 404 NOT FOUND status if the database is empty.
 * - GET /journal/id/{myid}: Retrieves a specific journal entry by its unique identifier
 *   (ObjectId). If the entry does not exist, a 404 NOT FOUND status is returned.
 * - DELETE /journal/id/{myid}: Deletes a specific journal entry by its unique identifier.
 *   Returns a 204 NO CONTENT status upon successful deletion.
 * - PUT /journal/id/{myid}: Updates a journal entry by its unique identifier. If the
 *   provided fields (title or content) are null or empty, the old values are retained.
 *   Returns a 200 OK status if the update succeeds, or a 404 NOT FOUND status if the
 *   entry does not exist.
 *
 * Exception Handling:
 * - POST requests return a 400 BAD REQUEST status if the input data is invalid or if
 *   an exception occurs during processing.
 */
@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;


    @GetMapping(value = "{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntryList();
        if(!all.isEmpty() && all != null){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "{userName}")
    public ResponseEntity<JournalEntry> saveEntry(@RequestBody JournalEntry journalEntry,@PathVariable String userName) {
        try{

            journalEntry.setDate(Date.valueOf(LocalDate.now()));
            journalEntryService.createEntry(journalEntry, userName);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "id/{myid}")
    public ResponseEntity<?> getEntryById(@PathVariable ObjectId myid){
        Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(myid);
        if(journalEntry.isPresent())
        {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "id/{userName}/{myid}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myid,@PathVariable String userName){
        journalEntryService.deleteEntryById(myid, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/id/{userName}/{myid}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry, @PathVariable String userName){
        JournalEntry oldEntry = journalEntryService.getEntryById(myid).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.createEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
