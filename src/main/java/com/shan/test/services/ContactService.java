package com.shan.test.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.shan.test.model.Contact;
import com.shan.test.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by ssrinivasan on 2/13/2017.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "contacts")
@Path("/contacts")
@Service
public class ContactService {

    @XmlElement(name = "contacts")
    private String uri1 = "/contacts/users";

    @XmlElement(name = "report")
    private String uri2 = "/contacts/test";

    @Autowired
    private ContactRepository contactRepository;
    public String getUri1() {
        return uri1;
    }

    public void setUri1(String uri1) {
        this.uri1 = uri1;
    }

    public String getUri2() {
        return uri2;
    }

    public void setUri2(String uri2) {
        this.uri2 = uri2;
    }
    @GET
    @Path("/test")
    public Response responseMsg() throws JsonProcessingException {
    List<Contact> contactList = contactRepository.findByLastName("Bauer");
        Gson gson = new Gson();
        return Response.status(200).entity(gson.toJson(contactList)).build();
    }
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_XML)
    public ContactService getServiceInfo() {
        return new ContactService();
    }
}
