package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 *
 * Endpoints:
 *  /reimbursements - (GET) retrieves all available reimbursements
 *        - (POST) adds a reimbursement -> want available only to admins?...
 *
 *  /reimbursements/{id} - (GET) gets a reimbursement by id
 *             - (PUT) updates a reimbursement -> want available only to admins?...
 *             - (DELETE) deletes a reimbursement -> want available only to admins?...
 *              - (PATCH) if you want to partially update a resource
 * */

public class ReimbursementController implements FrontController {

    {System.out.println("in ReimbursementController class");}

    private ReimbursementService reimbursementService = new ReimbursementService();
    private ObjectMapper om = new ObjectMapper();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Getting the attribute we set in the RequestHandler's handle() method
        System.out.println(("in reimbursement controller process()"));
        String path = (String) request.getAttribute("path");
        System.out.println("Path attribute: " + path);
        String p = "";
        if (!(path==null)){
            System.out.println("why are you here?");
            p = path.substring(0,1);
        }
        System.out.println("Path attribute: " + path);

        if (path == null || path.equals("")) { // http://localhost:8080/trms/reimbursements

            switch (request.getMethod()) {

                case "GET": {
                    System.out.println("Getting all reimbursements from the database...");
                    response.getWriter().write(om.writeValueAsString(reimbursementService.getAllReimbursements()));
                    break;
                }

                case "POST": {
                    // add the reimbursement (read from the request body) to the database
                    System.out.println("Posting new reimbursement to the database...");

                    Reimbursement r = om.readValue(request.getReader(), Reimbursement.class);

                    System.out.println("Reimbursement r = " + r);
                    reimbursementService.createReimbursement(r);
                    break;
                }

            }
        } // -> there IS a path attribute that we need to use in our logic
        else if (!p.equals("a") && !p.equals("b") && !p.equals("d") && !p.equals("e")
                && !p.equals("f") && !p.equals("g") && !p.equals("h") && !p.equals("i")
                && !p.equals("j") && !p.equals("k") && !p.equals("l") && !p.equals("m")
                && !p.equals("n") && !p.equals("o") && !p.equals("p") && !p.equals("q")
                && !p.equals("r") && !p.equals("s")) {
            //if there is NOT an 'non-number char' before the integer
            // save that attribute into an integer
            int reimbursementId = Integer.parseInt(path);

            Reimbursement r = null;

            switch (request.getMethod()) {
                case "GET": {
                    System.out.println("Getting a single reimbursement from the database...");
                    r = reimbursementService.searchReimbursementById(reimbursementId);
                    if (r != null) {
                        response.getWriter().write(om.writeValueAsString(r));
                    } else {
                        response.sendError(404, "Reimbursement not found");
                    }
                    break;
                }

                case "PUT": {
                    System.out.println("Updating a reimbursement in the database Controller");
                    r = om.readValue(request.getReader(), Reimbursement.class);
                    reimbursementService.updateReimbursement(r);
                    break;
                }

                case "DELETE": {
                    System.out.println("Deleting a reimbursement Controller");
                    reimbursementService.deleteReimbursement(reimbursementId);
                    break;
                }

                default: {
                    response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    break;
                }
            }
        } else {
            switch (p) {
                case "a": {
                    System.out.println("/a Updating additional_info Controller");
                    Reimbursement r;
                    r = om.readValue(request.getReader(), Reimbursement.class);
                    reimbursementService.uploadAdditionalInfoService(r);
                    break;
                }
                case "b": {
                    System.out.println("/b Approve Reimbursements Direct Supervisor Controller");
                    Reimbursement r;
                    r = om.readValue(request.getReader(), Reimbursement.class);
                    reimbursementService.approveReimbursementDirectSupervisorService(r);
                    break;
                }
                case "d": {
                    System.out.println("/d Decline A Reimbursement Controller");
                    Reimbursement r;
                    r = om.readValue(request.getReader(), Reimbursement.class);
                    reimbursementService.declineReimbursementService(r);
                    break;
                }
                case "e": {
                    System.out.println("/e Get Reimbursement By Employee ID Controller");
                    //strip the 'e'
                    path = path.substring(1);
                    System.out.println("path: " + path);
                    int employeeId = Integer.parseInt(path);
                    response.getWriter().write(om.writeValueAsString(reimbursementService.searchReimbursementByEmployeeId(employeeId)));
                    break;
                }
                case "f": {
                    System.out.println("/f Get Direct Supervisor Approved Pending Reimbursements Controller");

                    response.getWriter().write(om.writeValueAsString(reimbursementService.getDirectSupervisorApprovedPendingReimbursementsService()));
                    break;
                }
                case "g": {
                    System.out.println("/g Updating Grade Controller");
                    Reimbursement r;
                    r = om.readValue(request.getReader(), Reimbursement.class);
                    reimbursementService.uploadGradeService(r);
                    break;
                }
                case "h": {
                    System.out.println("/h Approve Reimbursements Direct Supervisor Controller");
                    Reimbursement r;
                    r = om.readValue(request.getReader(), Reimbursement.class);
                    reimbursementService.approveReimbursementDepartmentHeadService(r);
                    break;
                }
                case "i": {
                    System.out.println("/i Get Pending Reimbursements");
                    response.getWriter().write(om.writeValueAsString(reimbursementService.getPendingReimbursementsService()));
                    break;
                }
                case "j": {
                    System.out.println("/j Get Direct Supervisor And Department Head Approved Pending Reimbursements");
                    response.getWriter().write(om.writeValueAsString(reimbursementService.getDirectSupervisorAndDepartmentHeadApprovedPendingReimbursementsService()));
                    break;
                }
                case "k": {
                    System.out.println("/k Award A Reimbursement");
                    Reimbursement r;
                    r = om.readValue(request.getReader(), Reimbursement.class);
                    reimbursementService.awardReimbursementService(r);
                    break;
                }
                case "l": {
                    System.out.println("/l Adjust Amount Controller");
                    Reimbursement r;
                    r = om.readValue(request.getReader(), Reimbursement.class);
                    reimbursementService.adjustAmountService(r);
                    break;
                }
                case "m": {
                    System.out.println("/m Get Urgent Reimbursements");
                    response.getWriter().write(om.writeValueAsString(reimbursementService.getUrgentReimbursementsService()));
                    break;
                }
                case "n": {
                    System.out.println("/n Update Available Balance");
                    Reimbursement r;
                    r = om.readValue(request.getReader(), Reimbursement.class);
                    reimbursementService.updateAvailableBalanceService(r);
                    break;
                }
                case "o": {
                    System.out.println("/o Get Employee Data By Employee ID Controller");
                    //strip the 'o'
                    path = path.substring(1);
                    System.out.println("path: " + path);
                    int employeeId = Integer.parseInt(path);
                    response.getWriter().write(om.writeValueAsString(reimbursementService.getEmployeeDataByEmployeeIdService(employeeId)));
                    break;
                }
                case "p": {
                    System.out.println("/p Updating is_presentation_uploaded Controller");
                    Reimbursement r;
                    r = om.readValue(request.getReader(), Reimbursement.class);
                    reimbursementService.uploadPresentationService(r);
                    break;
                }
                case "q": {
                    System.out.println("/s Get Supervised Reimbursements Controller");
                    //strip the 'q'
                    path = path.substring(1);
                    System.out.println("path: " + path);
                    int directSuperVisorId = Integer.parseInt(path);
                    response.getWriter().write(om.writeValueAsString(reimbursementService.searchSupervisedReimbursements(directSuperVisorId)));
                    break;
                }
                case "r": {
                    System.out.println("/r Request Additional Info Controller");
                    Reimbursement r;
                    r = om.readValue(request.getReader(), Reimbursement.class);
                    reimbursementService.requestAddtionalInfoService(r);
                    break;
                }
                case "s": {
                    System.out.println("/s Get Supervised Pending Reimbursements Controller");
                    //strip the 's'
                    path = path.substring(1);
                    System.out.println("path: " + path);
                    int directSuperVisorId = Integer.parseInt(path);
                    response.getWriter().write(om.writeValueAsString(reimbursementService.searchSupervisedPendingReimbursements(directSuperVisorId)));
                    break;
                }

                default:{
                    System.out.println("no switch defined for " + p);
                }
            }
        }
    }
}
