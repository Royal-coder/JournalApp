package com.royal.JournalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Represents a user entity in the application.
 * This class is mapped to the "users" collection in the MongoDB database.
 *
 * The User class holds the information of a user, including their unique username, password,
 * and a list of journal entries associated with the user.
 *
 * Fields:
 * - `id`: Represents the unique identifier for each user document in the database.
 * - `userName`: Represents the unique username of the user. It is indexed to ensure uniqueness.
 * - `password`: Represents the password for the user. This field is mandatory.
 * - `journalEntryList`: A list of journal entries associated with this user. The list is stored
 *   as a database reference (DBRef) to maintain the relationship with the JournalEntry collection.
 *
 * Notes:
 * - Indexes on `userName` must be explicitly enabled by setting "spring.data.mongodb.auto-index-creation=true" in the application properties.
 * - The `@NonNull` annotation ensures that the `userName` and `password` fields cannot be null.
 * - Use the `@DBRef` annotation to reference related collections, in this case, JournalEntry.
 */
@Document(collection = "users")
@Data
public class User {

    @Id
    private ObjectId id;
    @Indexed(unique = true) //This annotation with index the userName vales and make sure no duplication as userName cannot be same. Index will not be done automatically. we have to define "spring.data.mongodb.auto-index-creation=true" in application.properties
    @NonNull                //This annotation make sure value is not null
    private String userName;
    @NonNull
    private String password;
    private List<String> roles;
    @DBRef                  //This annotation use to make DB reference . in this case we are making reference of journalEntry with Users
    private List<JournalEntry> journalEntryList = new ArrayList<>();

}
