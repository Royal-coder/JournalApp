package com.royal.JournalApp.controller;

import com.royal.JournalApp.entity.JournalEntry;
import com.royal.JournalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping
    public JournalEntry saveEntry(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDate(Date.valueOf(LocalDate.now()));
        journalEntryService.createEntry(journalEntry);
        return journalEntry;
    }

    @GetMapping(value = "/all")
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @GetMapping(value = "id/{myid}")
    public JournalEntry getEntryById(@PathVariable ObjectId myid){
        return journalEntryService.getEntryById(myid).orElse(null);
    }

    @DeleteMapping(value = "id/{myid}")
    public boolean deleteEntryById(@PathVariable ObjectId myid){
        journalEntryService.deleteEntryById(myid);
        return true;
    }

    @PutMapping(value = "/id/{myid}")
    public JournalEntry updateEntry(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.getEntryById(myid).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
        }
        journalEntryService.createEntry(oldEntry);
        return oldEntry;

    }
}
