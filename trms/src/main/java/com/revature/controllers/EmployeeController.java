package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.EmployeeServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeController implements FrontController {
    {System.out.println("in EmployeeController class");}

    private EmployeeServices employeeServices = new EmployeeServices();
    ObjectMapper om = new ObjectMapper();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.getWriter().write(om.writeValueAsString(employeeServices.getAllEmployees()));
    }

}
