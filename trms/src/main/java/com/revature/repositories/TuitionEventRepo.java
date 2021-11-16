package com.revature.repositories;

import com.revature.models.TuitionEvent;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TuitionEventRepo implements CrudRepository<TuitionEvent>{

    {System.out.println("in TuitionEventRepo class");}

    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    //CREATE
    @Override
    public TuitionEvent add(TuitionEvent a) {

        try (Connection conn = cu.getConnection()) {

            System.out.println(" inserting into tuition_events");
            String sql = "insert into tuition_events values (?, ?, ?) returning *";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getTuitionType());
            ps.setFloat(2, a.getCoveragePercent());
            ps.setString(3, a.getGradingFormat());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a.setTuitionType(rs.getString("tuition_type"));
            }

            if(a != null) {
                System.out.println("New tuition event added");
                return a;
            }
            else
                System.out.println("New tuition event added");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //READ
    @Override
    public TuitionEvent getById(Integer id) {

        /* try with resources -> a way to initialize a resource that will
        then be closed when we're done with it */

        try (Connection conn = cu.getConnection()) {
            String sql = "select * from tuition_event where tuition_event_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql); //helps prevent SQL Injection attacks
            ps.setInt(1, id); // parameter indexes start from 1 not 0

            ResultSet rs = ps.executeQuery();

            if (rs.next()){

                TuitionEvent a = new TuitionEvent();

                a.setTuitionTypeId(rs.getInt("tuition_type_id"));
                a.setTuitionType(rs.getString("first_name"));
                a.setCoveragePercent(rs.getFloat("coverage_percent"));
                a.setGradingFormat(rs.getString("grading_format"));

                return a;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public TuitionEvent getByTuitionType(String tuitionType) {
        // parentheses around Connection conn = cu.getConnection means *"with resources"
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from tuition_event where tuition_type = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tuitionType);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                TuitionEvent u = new TuitionEvent(
                        rs.getInt("tuition_event_id"),
                        rs.getString("tuition_type"),
                        rs.getFloat("coverage_percent"),
                        rs.getString("grading_format"));
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<TuitionEvent> getAll() {

        List<TuitionEvent> tuitionEvents = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql ="select * from tuition_events";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                TuitionEvent a = new TuitionEvent(
                        rs.getInt("tuition_event_id"),
                        rs.getString("tuition_type"),
                        rs.getFloat("coverage_percent"),
                        rs.getString("grading_format"));

                tuitionEvents.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tuitionEvents;
    }

    // UPDATE - this will eventually become a PUT Http Request
    @Override
    public void update(TuitionEvent a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update tuition_event set tuition_type = ?, coverage_percent = ?, grading_format = ?" +
                    " where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getTuitionType());
            ps.setFloat(2, a.getCoveragePercent());
            ps.setString(3, a.getGradingFormat());
            ps.setInt(4, a.getTuitionTypeId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //DELETE
    @Override
    public boolean delete(Integer id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "delete from tuition_event where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
