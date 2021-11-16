package com.revature.repositories;

import com.revature.models.Employee;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo implements CrudRepository<Employee> {

    {System.out.println("in EmployeeRepo class");}

    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    //CREATE
    @Override
    public Employee add(Employee a) {

        try (Connection conn = cu.getConnection()) {

            System.out.println(" inserting into employees");

            String sql = "insert into employees values (default, ?, ?, ?, ?, ?, ?, 7, ?, ?, ?, 1000) returning *";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, a.getFirstName());
            ps.setString(2, a.getLastName());
            ps.setString(3, a.getUsername());
            ps.setString(4, a.getPassword());
            ps.setInt(5, a.getDirectSupervisorId());
            ps.setString(6, a.getEmail());
            ps.setBoolean(7, a.getIsDepartmentHead());
            ps.setBoolean(8, a.getIsBenefitsCoordinator());
            ps.setBoolean(9, a.getIsDirectSupervisor());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a.setEmployeeId(rs.getInt("employee_id"));
            }

            if(a != null) {
                System.out.println("New employee added");
                return a;
            }
            else
                System.out.println("New employee added");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // READ
    @Override
    public Employee getById(Integer id) {

        try (Connection conn = cu.getConnection()) {
            String sql = "select * from employees where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql); //helps prevent SQL Injection attacks

            ps.setInt(1, id); // parameter indexes start from 1 not 0

            ResultSet rs = ps.executeQuery();

            if (rs.next()){

                Employee a = new Employee();

                a.setEmployeeId(rs.getInt("employee_id"));
                a.setFirstName(rs.getString("first_name"));
                a.setLastName(rs.getString("last_name"));
                a.setDirectSupervisorId(rs.getInt("direct_supervisor_id"));
                a.setEmail(rs.getString("email"));
                a.setIsDepartmentHead(rs.getBoolean("is_department_head"));
                a.setIsBenefitsCoordinator(rs.getBoolean("is_benefits_coordinator"));
                a.setIsDirectSupervisor(rs.getBoolean("is_direct_supervisor"));
                a.setAvailableBalance(rs.getFloat("available_balance"));

                return a;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Employee getByUsername(String username) {
        // parentheses around Connection conn = cu.getConnection means *"with resources"
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from employees where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Employee u = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("direct_supervisor_id"),
                        rs.getString("email"),
                        rs.getBoolean("is_department_head"),
                        rs.getBoolean("is_benefits_coordinator"),
                        rs.getBoolean("is_direct_supervisor"),
                        rs.getFloat("available_balance")
                );
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // try *"with resources" automatically closes resources after execution

        return null;
    }

    public List<Employee> getAll() {

        List<Employee> employees = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from employees";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Employee a = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("direct_supervisor_id"),
                        rs.getString("email"),
                        rs.getBoolean("is_department_head"),
                        rs.getBoolean("is_benefits_coordinator"),
                        rs.getBoolean("is_direct_supervisor"),
                        rs.getFloat("available_balance")
                );

                employees.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    // UPDATE - this will eventually become a PUT Http Request
    @Override
    public void update(Employee a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update employees set first_name = ?, last_name = ? where employee_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, a.getFirstName());
            ps.setString(2, a.getLastName());
            ps.setInt(3, a.getEmployeeId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //DELETE
    @Override
    public boolean delete(Integer id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "delete from employees where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
