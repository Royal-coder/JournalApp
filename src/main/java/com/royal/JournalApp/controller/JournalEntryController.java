package com.royal.JournalApp.controller;

import com.royal.JournalApp.entity.JournalEntry;
import com.royal.JournalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping
    public ResponseEntity<JournalEntry> saveEntry(@RequestBody JournalEntry journalEntry) {
        try{
            journalEntry.setDate(Date.valueOf(LocalDate.now()));
            journalEntryService.createEntry(journalEntry);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll() {
        List<JournalEntry> all = journalEntryService.getAll();
        if(!all.isEmpty() && all != null){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    @DeleteMapping(value = "id/{myid}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myid){
        journalEntryService.deleteEntryById(myid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/id/{myid}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.getEntryById(myid).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.createEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
