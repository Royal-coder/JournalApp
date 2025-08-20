package com.royal.JournalApp.service;

import com.royal.JournalApp.entity.Employee;
import com.royal.JournalApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public void saveData(Employee employee){
        employeeRepository.save(employee);
    }
}
