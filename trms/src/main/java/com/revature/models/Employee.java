package com.revature.models;

public class Employee {

    // Instance Variables
    public int employeeId = 0;
    public String firstName;
    public String lastName;
    private String username;
    private String password;
    public int directSupervisorId;
    public String email;
    public boolean isDepartmentHead;
    public boolean isBenefitsCoordinator;
    public boolean isDirectSupervisor;
    public float availableBalance;

    // Constructors
    public Employee() {
    }

    public Employee(int employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(int employeeId, String username, String password) {
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
    }

    public Employee(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public Employee(int employeeId, String firstName, String lastName, String username,
                    String password, int directSupervisorId, String email,
                    boolean isDepartmentHead, boolean isBenefitsCoordinator, boolean isDirectSupervisor,
                    float availableBalance) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.directSupervisorId = directSupervisorId;
        this.email = email;
        this.isDepartmentHead = isDepartmentHead;
        this.isBenefitsCoordinator = isBenefitsCoordinator;
        this.isDirectSupervisor = isDirectSupervisor;
        this.availableBalance = availableBalance;
    }

    // ----End constructors---

    // getters and setters


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDirectSupervisorId() {
        return directSupervisorId;
    }

    public void setDirectSupervisorId(int directSupervisorId) {
        this.directSupervisorId = directSupervisorId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsDepartmentHead() {
        return isDepartmentHead;
    }

    public void setIsDepartmentHead(boolean departmentHead) {
        isDepartmentHead = departmentHead;
    }

    public boolean getIsBenefitsCoordinator() {
        return isBenefitsCoordinator;
    }

    public void setIsBenefitsCoordinator(boolean benefitsCoordinator) {
        isBenefitsCoordinator = benefitsCoordinator;
    }

    public boolean getIsDirectSupervisor() {
        return isDirectSupervisor;
    }

    public void setIsDirectSupervisor(boolean isDirectSupervisor) {
        isDirectSupervisor = isDirectSupervisor;
    }

    public float getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(float availableBalance) {
        this.availableBalance = availableBalance;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", directSupervisorId='" + directSupervisorId + '\'' +
                ", email='" + email + '\'' +
                ", isDepartmentHead=" + isDepartmentHead + '\'' +
                ", isBenefitsCoordinator=" + isBenefitsCoordinator + '\'' +
                ", isDirectSupervisor=" + isDirectSupervisor +
                ", availableBalance=" + availableBalance +
                '}';
    }
}