package com.revature.repositories;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementRepo implements CrudRepository<Reimbursement>{

    {System.out.println("in ReimbursementRepo class");}

    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    //CREATE
    @Override
    public Reimbursement add(Reimbursement a) {

        try (Connection conn = cu.getConnection()) {

            System.out.println(" inserting into reimbursements");
            String sql = "insert into reimbursements values (default, ?, ?, ?, ?, ?, 0, 0, ?, ?, ?, ' ', ' ', true, false, false, false, ?, ' ', false, false, false, false, 0, false, false, 1000) returning *";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, a.getEmployeeId());
            ps.setDate(2, a.getClaimDate());
            ps.setDate(3, a.getEventStartDate());
            ps.setDate(4, a.getEventEndDate());
            ps.setFloat(5, a.getCost());
            ps.setString(6, a.getEventLocation());
            ps.setString(7, a.getTuitionType());
            ps.setString(8, a.getWorkJustification());
            ps.setString(9, a.getEventName());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a.setReimbursementId(rs.getInt("reimbursement_id"));
            }

            if(a != null) {
                System.out.println("New reimbursement added");

                //update for claim amounts
                String sql2 = "update reimbursements set claim_amount = tuition_events.coverage_percent * cost from tuition_events where tuition_events.tuition_type = reimbursements.tuition_type returning *";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ResultSet rs2 = ps2.executeQuery();
                System.out.println(rs2);

                //update for direct supervisor ids
                String sql3 = "update reimbursements set direct_supervisor_id = employees.direct_supervisor_id from employees where employees.employee_id = reimbursements.employee_id returning *";
                PreparedStatement ps3 = conn.prepareStatement(sql3);
                ResultSet rs3 = ps3.executeQuery();
                System.out.println(rs3);

                //update for is department head flag
                String sql4 = "update reimbursements set is_department_head = employees.is_department_head from employees where employees.employee_id = reimbursements.direct_supervisor_id returning *";
                PreparedStatement ps4 = conn.prepareStatement(sql4);
                ResultSet rs4 = ps4.executeQuery();
                System.out.println(rs4);

                return a;
            }
            else
                System.out.println("New reimbursement NOT added");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //READ
    @Override
    public Reimbursement getById(Integer id) {

        try (Connection conn = cu.getConnection()) {
            String sql = "select * from reimbursements where reimbursement_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){

                Reimbursement a = new Reimbursement();

                a.setReimbursementId(rs.getInt("reimbursement_id"));
                a.setEmployeeId(rs.getInt("employee_id"));
                a.setClaimDate(rs.getDate("claim_date"));
                a.setEventStartDate(rs.getDate("event_start_date"));
                a.setEventEndDate(rs.getDate("event_end_date"));
                a.setCost((rs.getFloat("cost")));
                a.setClaimAmount((rs.getFloat("claim_amount")));
                a.setApprovedAmount(rs.getFloat("approved_amount"));
                a.setEventLocation(rs.getString("event_location"));
                a.setTuitionType(rs.getString("tuition_type"));
                a.setWorkJustification(rs.getString("work_justification"));
                a.setGrade(rs.getString("grade"));
                a.setDirectSupervisorMessage(rs.getString("direct_supervisor_message"));
                a.setIsPending(rs.getBoolean("is_pending"));
                a.setIsAwarded(rs.getBoolean("is_awarded"));
                a.setIsDeclined(rs.getBoolean("is_declined"));
                a.setIsArchived(rs.getBoolean("is_archived"));
                a.setEventName(rs.getString("event_name"));
                a.setAdditionalInfo(rs.getString("additional_info"));
                a.setIsPresentationUploaded(rs.getBoolean("is_presentation_uploaded"));
                a.setIsDirectSupervisorApproved(rs.getBoolean("is_direct_supervisor_approved"));
                a.setIsDepartmentHeadApproved(rs.getBoolean("is_department_head_approved"));
                a.setIsRequestAdditionalInfo(rs.getBoolean("is_request_additional_info"));
                a.setDirectSupervisorId(rs.getInt("direct_supervisor_id"));
                a.setIsUrgent(rs.getBoolean("is_urgent"));
                a.setIsDepartmentHead(rs.getBoolean("is_department_head"));
                a.setAvailableBalance(rs.getFloat("available_balance"));

                return a;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reimbursement> getByEmployeeId(Integer id) {

        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from reimbursements where employee_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // For each record we need to create a Reimbursement Object

                Reimbursement r = new Reimbursement();
                r.setReimbursementId(rs.getInt("reimbursement_id"));
                r.setEmployeeId(rs.getInt("employee_id"));
                r.setClaimDate(rs.getDate("claim_date"));
                r.setEventStartDate(rs.getDate("event_start_date"));
                r.setEventEndDate(rs.getDate("event_end_date"));
                r.setCost(rs.getFloat("cost"));
                r.setClaimAmount(rs.getFloat("claim_amount"));
                r.setApprovedAmount(rs.getFloat("approved_amount"));
                r.setEventLocation(rs.getString("event_location"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setWorkJustification(rs.getString("work_justification"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setGrade(rs.getString("grade"));
                r.setDirectSupervisorMessage(rs.getString("direct_supervisor_message"));
                r.setIsPending(rs.getBoolean("is_pending"));
                r.setIsAwarded(rs.getBoolean("is_awarded"));
                r.setIsDeclined(rs.getBoolean("is_declined"));
                r.setIsArchived(rs.getBoolean("is_archived"));
                r.setEventName(rs.getString("event_name"));
                r.setAdditionalInfo(rs.getString("additional_info"));
                r.setIsPresentationUploaded(rs.getBoolean("is_presentation_uploaded"));
                r.setIsDirectSupervisorApproved(rs.getBoolean("is_direct_supervisor_approved"));
                r.setIsDepartmentHeadApproved(rs.getBoolean("is_department_head_approved"));
                r.setIsRequestAdditionalInfo(rs.getBoolean("is_request_additional_info"));
                r.setDirectSupervisorId(rs.getInt("direct_supervisor_id"));
                r.setIsUrgent(rs.getBoolean("is_urgent"));
                r.setIsDepartmentHead(rs.getBoolean("is_department_head"));
                r.setAvailableBalance(rs.getFloat("available_balance"));

                reimbursements.add(r);
            }

            return reimbursements;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Employee> getEmployeeDataByEmployeeId(Integer id) {

        List<Employee> employees = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from employees where employee_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // For each record we need to create a Employee Object

                Employee r = new Employee();

                r.setEmployeeId(rs.getInt("employee_id"));
                r.setFirstName(rs.getString("first_name"));
                r.setLastName(rs.getString("last_name"));
                r.setUsername(rs.getString("username"));
                r.setPassword(rs.getString("password"));
                r.setEmail(rs.getString("email"));
                r.setDirectSupervisorId(rs.getInt("direct_supervisor_id"));
                r.setIsBenefitsCoordinator(rs.getBoolean("is_benefits_coordinator"));
                r.setIsDepartmentHead(rs.getBoolean("is_department_head"));
                r.setAvailableBalance(rs.getFloat("available_balance"));

                employees.add(r);
            }

            return employees;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Reimbursement> getSupervisedPendingReimbursements(Integer id) {

        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from reimbursements where direct_supervisor_id = ? and is_pending = true";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // For each record we need to create a Reimbursement Object

                Reimbursement r = new Reimbursement();
                r.setReimbursementId(rs.getInt("reimbursement_id"));
                r.setEmployeeId(rs.getInt("employee_id"));
                r.setClaimDate(rs.getDate("claim_date"));
                r.setEventStartDate(rs.getDate("event_start_date"));
                r.setEventEndDate(rs.getDate("event_end_date"));
                r.setCost(rs.getFloat("cost"));
                r.setClaimAmount(rs.getFloat("claim_amount"));
                r.setApprovedAmount(rs.getFloat("approved_amount"));
                r.setEventLocation(rs.getString("event_location"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setWorkJustification(rs.getString("work_justification"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setGrade(rs.getString("grade"));
                r.setDirectSupervisorMessage(rs.getString("direct_supervisor_message"));
                r.setIsPending(rs.getBoolean("is_pending"));
                r.setIsAwarded(rs.getBoolean("is_awarded"));
                r.setIsDeclined(rs.getBoolean("is_declined"));
                r.setIsArchived(rs.getBoolean("is_archived"));
                r.setEventName(rs.getString("event_name"));
                r.setAdditionalInfo(rs.getString("additional_info"));
                r.setIsPresentationUploaded(rs.getBoolean("is_presentation_uploaded"));
                r.setIsDirectSupervisorApproved(rs.getBoolean("is_direct_supervisor_approved"));
                r.setIsDepartmentHeadApproved(rs.getBoolean("is_department_head_approved"));
                r.setIsRequestAdditionalInfo(rs.getBoolean("is_request_additional_info"));
                r.setDirectSupervisorId(rs.getInt("direct_supervisor_id"));
                r.setIsUrgent(rs.getBoolean("is_urgent"));
                r.setIsDepartmentHead(rs.getBoolean("is_department_head"));
                r.setAvailableBalance(rs.getFloat("available_balance"));

                reimbursements.add(r);
            }

            return reimbursements;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Reimbursement> getSupervisedReimbursements(Integer id) {

        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from reimbursements where direct_supervisor_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // For each record we need to create a Reimbursement Object

                Reimbursement r = new Reimbursement();
                r.setReimbursementId(rs.getInt("reimbursement_id"));
                r.setEmployeeId(rs.getInt("employee_id"));
                r.setClaimDate(rs.getDate("claim_date"));
                r.setEventStartDate(rs.getDate("event_start_date"));
                r.setEventEndDate(rs.getDate("event_end_date"));
                r.setCost(rs.getFloat("cost"));
                r.setClaimAmount(rs.getFloat("claim_amount"));
                r.setApprovedAmount(rs.getFloat("approved_amount"));
                r.setEventLocation(rs.getString("event_location"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setWorkJustification(rs.getString("work_justification"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setGrade(rs.getString("grade"));
                r.setDirectSupervisorMessage(rs.getString("direct_supervisor_message"));
                r.setIsPending(rs.getBoolean("is_pending"));
                r.setIsAwarded(rs.getBoolean("is_awarded"));
                r.setIsDeclined(rs.getBoolean("is_declined"));
                r.setIsArchived(rs.getBoolean("is_archived"));
                r.setEventName(rs.getString("event_name"));
                r.setAdditionalInfo(rs.getString("additional_info"));
                r.setIsPresentationUploaded(rs.getBoolean("is_presentation_uploaded"));
                r.setIsDirectSupervisorApproved(rs.getBoolean("is_direct_supervisor_approved"));
                r.setIsDepartmentHeadApproved(rs.getBoolean("is_department_head_approved"));
                r.setIsRequestAdditionalInfo(rs.getBoolean("is_request_additional_info"));
                r.setDirectSupervisorId(rs.getInt("direct_supervisor_id"));
                r.setIsUrgent(rs.getBoolean("is_urgent"));
                r.setIsDepartmentHead(rs.getBoolean("is_department_head"));
                r.setAvailableBalance(rs.getFloat("available_balance"));

                reimbursements.add(r);
            }

            return reimbursements;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Reimbursement> getDirectSupervisorApprovedPendingReimbursements() {

        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from reimbursements where is_pending = true and is_direct_supervisor_approved = true";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement r = new Reimbursement();

                r.setReimbursementId(rs.getInt("reimbursement_id"));
                r.setEmployeeId(rs.getInt("employee_id"));
                r.setClaimDate(rs.getDate("claim_date"));
                r.setEventStartDate(rs.getDate("event_start_date"));
                r.setEventEndDate(rs.getDate("event_end_date"));
                r.setCost(rs.getFloat("cost"));
                r.setClaimAmount(rs.getFloat("claim_amount"));
                r.setApprovedAmount(rs.getFloat("approved_amount"));
                r.setEventLocation(rs.getString("event_location"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setWorkJustification(rs.getString("work_justification"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setGrade(rs.getString("grade"));
                r.setDirectSupervisorMessage(rs.getString("direct_supervisor_message"));
                r.setIsPending(rs.getBoolean("is_pending"));
                r.setIsAwarded(rs.getBoolean("is_awarded"));
                r.setIsDeclined(rs.getBoolean("is_declined"));
                r.setIsArchived(rs.getBoolean("is_archived"));
                r.setEventName(rs.getString("event_name"));
                r.setAdditionalInfo(rs.getString("additional_info"));
                r.setIsPresentationUploaded(rs.getBoolean("is_presentation_uploaded"));
                r.setIsDirectSupervisorApproved(rs.getBoolean("is_direct_supervisor_approved"));
                r.setIsDepartmentHeadApproved(rs.getBoolean("is_department_head_approved"));
                r.setIsRequestAdditionalInfo(rs.getBoolean("is_request_additional_info"));
                r.setDirectSupervisorId(rs.getInt("direct_supervisor_id"));
                r.setIsUrgent(rs.getBoolean("is_urgent"));
                r.setIsDepartmentHead(rs.getBoolean("is_department_head"));
                r.setAvailableBalance(rs.getFloat("available_balance"));

                reimbursements.add(r);
            }

            return reimbursements;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Reimbursement> getPendingReimbursements() {

        List<Reimbursement> reimbursements = new ArrayList<>();
        try (Connection conn = cu.getConnection()) {
            String sql = "select * from reimbursements where is_pending = true";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reimbursement r = new Reimbursement();

                r.setReimbursementId(rs.getInt("reimbursement_id"));
                r.setEmployeeId(rs.getInt("employee_id"));
                r.setClaimDate(rs.getDate("claim_date"));
                r.setEventStartDate(rs.getDate("event_start_date"));
                r.setEventEndDate(rs.getDate("event_end_date"));
                r.setCost(rs.getFloat("cost"));
                r.setClaimAmount(rs.getFloat("claim_amount"));
                r.setApprovedAmount(rs.getFloat("approved_amount"));
                r.setEventLocation(rs.getString("event_location"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setWorkJustification(rs.getString("work_justification"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setGrade(rs.getString("grade"));
                r.setDirectSupervisorMessage(rs.getString("direct_supervisor_message"));
                r.setIsPending(rs.getBoolean("is_pending"));
                r.setIsAwarded(rs.getBoolean("is_awarded"));
                r.setIsDeclined(rs.getBoolean("is_declined"));
                r.setIsArchived(rs.getBoolean("is_archived"));
                r.setEventName(rs.getString("event_name"));
                r.setAdditionalInfo(rs.getString("additional_info"));
                r.setIsPresentationUploaded(rs.getBoolean("is_presentation_uploaded"));
                r.setIsDirectSupervisorApproved(rs.getBoolean("is_direct_supervisor_approved"));
                r.setIsDepartmentHeadApproved(rs.getBoolean("is_department_head_approved"));
                r.setIsRequestAdditionalInfo(rs.getBoolean("is_request_additional_info"));
                r.setDirectSupervisorId(rs.getInt("direct_supervisor_id"));
                r.setIsUrgent(rs.getBoolean("is_urgent"));
                r.setIsDepartmentHead(rs.getBoolean("is_department_head"));
                r.setAvailableBalance(rs.getFloat("available_balance"));

                reimbursements.add(r);
            }
            return reimbursements;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reimbursement> getUrgentReimbursements() {

        List<Reimbursement> reimbursements = new ArrayList<>();
        try (Connection conn = cu.getConnection()) {
            String sql = "select * from reimbursements where is_urgent = true";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reimbursement r = new Reimbursement();

                r.setReimbursementId(rs.getInt("reimbursement_id"));
                r.setEmployeeId(rs.getInt("employee_id"));
                r.setClaimDate(rs.getDate("claim_date"));
                r.setEventStartDate(rs.getDate("event_start_date"));
                r.setEventEndDate(rs.getDate("event_end_date"));
                r.setCost(rs.getFloat("cost"));
                r.setClaimAmount(rs.getFloat("claim_amount"));
                r.setApprovedAmount(rs.getFloat("approved_amount"));
                r.setEventLocation(rs.getString("event_location"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setWorkJustification(rs.getString("work_justification"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setGrade(rs.getString("grade"));
                r.setDirectSupervisorMessage(rs.getString("direct_supervisor_message"));
                r.setIsPending(rs.getBoolean("is_pending"));
                r.setIsAwarded(rs.getBoolean("is_awarded"));
                r.setIsDeclined(rs.getBoolean("is_declined"));
                r.setIsArchived(rs.getBoolean("is_archived"));
                r.setEventName(rs.getString("event_name"));
                r.setAdditionalInfo(rs.getString("additional_info"));
                r.setIsPresentationUploaded(rs.getBoolean("is_presentation_uploaded"));
                r.setIsDirectSupervisorApproved(rs.getBoolean("is_direct_supervisor_approved"));
                r.setIsDepartmentHeadApproved(rs.getBoolean("is_department_head_approved"));
                r.setIsRequestAdditionalInfo(rs.getBoolean("is_request_additional_info"));
                r.setDirectSupervisorId(rs.getInt("direct_supervisor_id"));
                r.setIsUrgent(rs.getBoolean("is_urgent"));
                r.setIsDepartmentHead(rs.getBoolean("is_department_head"));
                r.setAvailableBalance(rs.getFloat("available_balance"));

                reimbursements.add(r);
            }
            return reimbursements;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reimbursement> getDirectSupervisorAndDepartmentHeadApprovedPendingReimbursements() {

        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from reimbursements where is_pending = true and is_direct_supervisor_approved = true and is_department_head_approved = true";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement r = new Reimbursement();

                r.setReimbursementId(rs.getInt("reimbursement_id"));
                r.setEmployeeId(rs.getInt("employee_id"));
                r.setClaimDate(rs.getDate("claim_date"));
                r.setEventStartDate(rs.getDate("event_start_date"));
                r.setEventEndDate(rs.getDate("event_end_date"));
                r.setCost(rs.getFloat("cost"));
                r.setClaimAmount(rs.getFloat("claim_amount"));
                r.setApprovedAmount(rs.getFloat("approved_amount"));
                r.setEventLocation(rs.getString("event_location"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setWorkJustification(rs.getString("work_justification"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setGrade(rs.getString("grade"));
                r.setDirectSupervisorMessage(rs.getString("direct_supervisor_message"));
                r.setIsPending(rs.getBoolean("is_pending"));
                r.setIsAwarded(rs.getBoolean("is_awarded"));
                r.setIsDeclined(rs.getBoolean("is_declined"));
                r.setIsArchived(rs.getBoolean("is_archived"));
                r.setEventName(rs.getString("event_name"));
                r.setAdditionalInfo(rs.getString("additional_info"));
                r.setIsPresentationUploaded(rs.getBoolean("is_presentation_uploaded"));
                r.setIsDirectSupervisorApproved(rs.getBoolean("is_direct_supervisor_approved"));
                r.setIsDepartmentHeadApproved(rs.getBoolean("is_department_head_approved"));
                r.setIsRequestAdditionalInfo(rs.getBoolean("is_request_additional_info"));
                r.setDirectSupervisorId(rs.getInt("direct_supervisor_id"));
                r.setIsUrgent(rs.getBoolean("is_urgent"));
                r.setIsDepartmentHead(rs.getBoolean("is_department_head"));
                r.setAvailableBalance(rs.getFloat("available_balance"));

                reimbursements.add(r);
            }

            return reimbursements;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Reimbursement> getAll() {

        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            //update for urgent
            String sql2 = "update reimbursements set is_urgent = true where current_date + 14 >= event_start_date and is_pending = true returning *";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();
            System.out.println(rs2);

            //update for auto approve Direct Supervisor
            String sql3 = "update reimbursements set is_direct_supervisor_approved = true where current_date + 4 >= event_start_date and is_pending = true returning *";
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ResultSet rs3 = ps3.executeQuery();
            System.out.println(rs3);

            //update for auto approve Department Head
            String sql4 = "update reimbursements set is_department_head_approved = true where current_date + 2 >= event_start_date and is_pending = true returning *";
            PreparedStatement ps4 = conn.prepareStatement(sql4);
            ResultSet rs4 = ps4.executeQuery();
            System.out.println(rs4);

            //update for archiving
            String sql5 = "update reimbursements set is_archived = true where Extract(year from event_start_date) < Extract(year from current_date) and is_pending = false and (is_declined = true or is_awarded = true) and is_archived = false returning *";
            PreparedStatement ps5 = conn.prepareStatement(sql5);
            ResultSet rs5 = ps5.executeQuery();
            System.out.println(rs5);

            String sql = "select * from reimbursements";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // For each record we need to create a Reimbursement Object

                Reimbursement r = new Reimbursement();
                r.setReimbursementId(rs.getInt("reimbursement_id"));
                r.setEmployeeId(rs.getInt("employee_id"));
                r.setClaimDate(rs.getDate("claim_date"));
                r.setEventStartDate(rs.getDate("event_start_date"));
                r.setEventEndDate(rs.getDate("event_end_date"));
                r.setCost(rs.getFloat("cost"));
                r.setClaimAmount(rs.getFloat("claim_amount"));
                r.setApprovedAmount(rs.getFloat("approved_amount"));
                r.setEventLocation(rs.getString("event_location"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setWorkJustification(rs.getString("work_justification"));
                r.setTuitionType(rs.getString("tuition_type"));
                r.setGrade(rs.getString("grade"));
                r.setDirectSupervisorMessage(rs.getString("direct_supervisor_message"));
                r.setIsPending(rs.getBoolean("is_pending"));
                r.setIsAwarded(rs.getBoolean("is_awarded"));
                r.setIsDeclined(rs.getBoolean("is_declined"));
                r.setIsArchived(rs.getBoolean("is_archived"));
                r.setEventName(rs.getString("event_name"));
                r.setAdditionalInfo(rs.getString("additional_info"));
                r.setIsPresentationUploaded(rs.getBoolean("is_presentation_uploaded"));
                r.setIsDirectSupervisorApproved(rs.getBoolean("is_direct_supervisor_approved"));
                r.setIsDepartmentHeadApproved(rs.getBoolean("is_department_head_approved"));
                r.setIsRequestAdditionalInfo(rs.getBoolean("is_request_additional_info"));
                r.setDirectSupervisorId(rs.getInt("direct_supervisor_id"));
                r.setIsUrgent(rs.getBoolean("is_urgent"));
                r.setIsDepartmentHead(rs.getBoolean("is_department_head"));
                r.setAvailableBalance(rs.getFloat("available_balance"));

                reimbursements.add(r);
            }

            return reimbursements;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE - this will eventually become a PUT Http Request
    @Override
    public void update(Reimbursement a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursements set grade = ?, additional_info = ? where reimbursement_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, a.getGrade());
            ps.setString(2, a.getAdditionalInfo());
            ps.setInt(3, a.getReimbursementId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAvailableBalance(Reimbursement a) {
        try (Connection conn = cu.getConnection()) {
            String sql = "update reimbursements set available_balance = (select 1000-sum(claim_amount) from reimbursements r where (employee_id = ?) and (is_pending = true or is_awarded = true) and (Extract(year from event_start_date) = Extract(year from current_date))) where employee_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, a.getEmployeeId());
            ps.setInt(2, a.getEmployeeId());
            ps.execute();

            String sql2 = "update reimbursements set approved_amount = claim_amount + available_balance where available_balance < 0 and employee_id = ? and approved_amount = 0";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, a.getEmployeeId());
            ps2.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void uploadPresentation(Reimbursement a) {
        try (Connection conn = cu.getConnection()) {
            String sql = "update reimbursements set is_presentation_uploaded = ? where reimbursement_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, a.getIsPresentationUploaded());
            ps.setInt(2, a.getReimbursementId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void requestAdditionalInfo(Reimbursement a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursements set is_request_additional_info = ? where reimbursement_id = ?";

            System.out.println(sql);

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setBoolean(1, a.getIsRequestAdditionalInfo());
            ps.setInt(2, a.getReimbursementId());

            System.out.println(ps);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void approveReimbursementDirectSupervisor(Reimbursement a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursements set is_direct_supervisor_approved = ? where reimbursement_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, a.getIsDirectSupervisorApproved());
            ps.setInt(2, a.getReimbursementId());
            ps.execute();

            //auto update Department Head Approval if Direct Supervisor is also a Department Head
            String sql2 = "if emloyees.is_department_head = true then update reimbursements set is_department_head_approved = true where reimbursement_id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, a.getReimbursementId());
            ps2.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void approveReimbursementDepartmentHead(Reimbursement a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursements set is_department_head_approved = ? where reimbursement_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setBoolean(1, a.getIsDepartmentHeadApproved());
            ps.setInt(2, a.getReimbursementId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void awardReimbursement(Reimbursement a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursements set is_awarded = ?, is_pending = ?, approved_amount = claim_amount, is_urgent = false where reimbursement_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setBoolean(1, a.getIsAwarded());
            ps.setBoolean(2, a.getIsPending());
            ps.setInt(3, a.getReimbursementId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adjustAmount(Reimbursement a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursements set approved_amount = ?, direct_supervisor_message = ? where reimbursement_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setFloat(1, a.getApprovedAmount());
            ps.setString(2, a.getDirectSupervisorMessage());
            ps.setInt(3, a.getReimbursementId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void declineReimbursement(Reimbursement a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursements set is_declined = ?, is_pending = ?, direct_supervisor_message = ?, is_urgent = false where reimbursement_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setBoolean(1, a.getIsDeclined());
            ps.setBoolean(2, a.getIsPending());
            ps.setString(3, a.getDirectSupervisorMessage());
            ps.setInt(4, a.getReimbursementId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void uploadGrade(Reimbursement a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursements set grade = ? where reimbursement_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, a.getGrade());
            ps.setInt(2, a.getReimbursementId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void uploadAdditionalInfo(Reimbursement a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursements set additional_info = ? where reimbursement_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, a.getAdditionalInfo());
            ps.setInt(2, a.getReimbursementId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //DELETE
    @Override
    public boolean delete(Integer id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "delete from reimbursements where reimbursement_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}