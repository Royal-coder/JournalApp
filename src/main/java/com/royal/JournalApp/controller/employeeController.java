package com.royal.JournalApp.controller;

import com.royal.JournalApp.entity.Employee;
import com.royal.JournalApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Random;

@Controller
public class employeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/registration_form")
    public String showRegistrationForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "registration_form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee, Model model) {
        // Save employee, e.g. to DB, then display result
        model.addAttribute("employee", employee);
        Random rand = new Random();
        int randomInt = 100000 + rand.nextInt(900000);
        employee.setUniqueId((long) randomInt);
        employeeService.saveData(employee);
        return "display_form"; // Render a result page
    }
}
