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

    @DBRef                  //This annotation use to make DB reference . in this case we are making reference of journalEntry with Users
    private List<JournalEntry> journalEntryList = new ArrayList<>();

}
