package com.royal.JournalApp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document("employee")
@Data
@AllArgsConstructor
public class Employee {
    @Id
    private Long uniqueId;
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String dept;
    @NonNull
    private Integer age;
    @NonNull
    private String gender;
    @NonNull
    private LocalDate joiningDate;
    @NonNull
    private LocalDate retiringDate;

    public Employee() {

    }
}

