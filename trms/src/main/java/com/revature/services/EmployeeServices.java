package com.revature.services;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeRepo;

import java.util.List;

public class EmployeeServices {

    {System.out.println("in EmployeeServices class");}

    EmployeeRepo employeeRepo = new EmployeeRepo();

    public Employee login(String username, String password) {
        //int returnId = 0;
        // in order to log in a user, we will need username and password
        // that information is stored in our database
        // the repository layer needs to take care of this

        Employee e = employeeRepo.getByUsername(username); //more of the Sole Responsibility Principle at work

        // check to make sure User object is not null
        if (e != null) {
            // now check to make sure it matches
            if (username.equals(e.getUsername()) && password.equals(e.getPassword())) {
                return e;

            }
        }

        return null;
    }

    public List<Employee> getAllEmployees() {

        return employeeRepo.getAll();

    }
}
