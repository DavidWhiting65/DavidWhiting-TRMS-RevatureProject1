package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.revature.services.EmployeeServices;
//import org.apache.tomcat.util.http.parser.Cookie;


import javax.servlet.ServletException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController implements FrontController {

    {System.out.println("in LoginController class");}

    static Employee e = new Employee();

    private Logger log = LogManager.getLogger(LoginController.class);
    private EmployeeServices employeeServices = new EmployeeServices();

    private ObjectMapper om = new ObjectMapper();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("Username: " + username + " Password: " + password);
        e = employeeServices.login(username,password);

        String jsonString = om.writeValueAsString(e);
        System.out.println("jsonString: " + jsonString);
        Cookie cookie = new Cookie("firstNameCookie", e.firstName);
        response.addCookie(cookie);

        System.out.println("Employee after login: " + e.toString());

        if (e.employeeId > 0) { System.out.println("e.isDirectSupervisor: " + e.isDirectSupervisor);
            if (e.isDirectSupervisor == true) {
                System.out.println(" why here? e.isDirectSupervisor: " + e.isDirectSupervisor);
                response.sendRedirect("static/directsupervisors.html");
            } else if (e.isDepartmentHead == true) {
                response.sendRedirect("static/departmentheads.html");
            } else if (e.isBenefitsCoordinator == true) {
                response.sendRedirect("static/benefitscoordinators.html");
            } else {
            response.sendRedirect("static/employees.html");
            }
            //add cookie ??
            // add object mapper??
            //response.getWriter().write(om.writeValueAsString(e));

        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid login credentials");
        }
    }
}
