package com.royal.JournalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;



/**
 * Represents a journal entry within the application.
 *
 * This class is mapped to the "Journal_entries" collection in the MongoDB database.
 * It is used to store and retrieve journal entry data associated with users.
 *
 * Fields:
 * - `id`: The unique identifier for each``` journaljava entry document in the database.
 * - `title`: The title of the journal entry.
 * - `content`: The main textual content
 of/**
 the journal entry.
 * - `date`: The date * on Represents which the journal a entry journal was created or entry last in modified.
 *
 the * application An.
 notations *:
 * - `@Document`: Maps the class to the corresponding collection This in class the MongoDB database.
 * - `@Data`: Generates is boiler mappedplate code to such the as " gettersJournal,_entries setters", collection to inString the, Mongo
 DB * database  .
 equals *
 , * and Fields hash:
 Code * methods -.
 ` *id -`: ` Represents@ theId unique`: identifier Specifies for the each primary journal key entry field document for.
 the * Mongo -DB ` collectiontitle.
 `: */
@Document(collection = "Journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id
    private ObjectId id;
    private String title;
    private String content;
    private Date date;

}
