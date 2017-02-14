package com.shan.test.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by ssrinivasan on 2/13/2017.
 */
@Path("/contacts")
public class ContactService {
    @GET
    @Path("/test")
    public Response responseMsg() {
        String response = "all contacts here";
        return Response.status(200).entity(response).build();
    }

}
