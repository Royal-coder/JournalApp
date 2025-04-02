package com.royal.JournalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;



@Document(collection = "Journal_entries")
@Data
public class JournalEntry {

    @Id
    private ObjectId id;
    private String title;
    private String content;
    private Date date;

}
