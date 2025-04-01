package com.royal.JournalApp.service;

import com.royal.JournalApp.entity.JournalEntry;
import com.royal.JournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void createEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);

    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId myid){
        return journalEntryRepository.findById(myid);
    }

    public void deleteEntryById(ObjectId myid){
        journalEntryRepository.deleteById(myid);
    }

    public void updateEntry(ObjectId id,JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
}
