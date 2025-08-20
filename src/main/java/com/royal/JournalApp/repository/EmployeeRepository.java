package com.royal.JournalApp.repository;

import com.royal.JournalApp.entity.Employee;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {
}
