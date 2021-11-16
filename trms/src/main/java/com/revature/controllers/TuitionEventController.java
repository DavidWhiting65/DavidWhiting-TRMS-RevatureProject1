package com.revature.controllers;

/*
Endpoints:

    /tuitionevents -> (GET) - we want to retrieve all of the tuition events from the database
                (POST) - we want to add a new tuition event to the database

    /tuitionevents/{id} -> (GET) - return a specific (and single) resource from the database
                    (PUT) - update a resource
                     (DELETE) - delete a resource
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.TuitionEvent;
import com.revature.services.TuitionEventService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TuitionEventController implements FrontController {

    {System.out.println("in TuitionEventController class");}

    private ObjectMapper om = new ObjectMapper();
    private TuitionEventService tuitionEventService = new TuitionEventService();


    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path = (String) request.getAttribute("path");
        System.out.println("Path attribute: " + path);

        if (path == null || path.equals("")) {


            switch (request.getMethod()) {

                case "GET": {
                    // maybe this is a l=place where you want to check a user's permission/role
                    System.out.println("Getting all tuition events...");

                    List<TuitionEvent> authors = tuitionEventService.getAllTuitionEvents();
                    response.getWriter().write(om.writeValueAsString(authors));
                    break;
                }

                case "POST": {
                    System.out.println("Creating new tuition event...");
                    TuitionEvent a = om.readValue(request.getReader(), TuitionEvent.class);

//                    a.setId((tuitionEventService.createTuitionEvent(a)).getId());
                    a = tuitionEventService.createTuitionEvent(a);
                    //response.setStatus(201); // Resource created
                    response.getWriter().write(om.writeValueAsString(a));
                    break;
                }
            }

        } else {

            int tuitionEventId = Integer.parseInt(path);

            switch (request.getMethod()) {

                case "GET": {
                    TuitionEvent a = tuitionEventService.searchTuitionEventById(tuitionEventId);
                    if (a != null) {
                        response.getWriter().write(om.writeValueAsString(a));
                    } else {
                        response.sendError(404, "Tuition Event not found");
                    }
                    break;
                }

                case "PUT": {
                    TuitionEvent a = om.readValue(request.getReader(), TuitionEvent.class);
                    tuitionEventService.updateTuitionEvent(a);
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    // 204 - server doesn't have any content to send back, but the request was successful
                    break;
                }

                case "DELETE": {
                    tuitionEventService.deleteTuitionEvent(tuitionEventId);
                    response.setStatus(204);
                    break;
                }

                default: {
                    response.sendError(405);
                    break;
                }
            }
        }
    }
}
