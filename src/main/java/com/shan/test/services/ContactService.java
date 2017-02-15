package com.shan.test.services;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.shan.test.model.Contact;
import com.shan.test.model.Contacts;
import com.shan.test.repository.ContactRepository;

/**
 * Created by ssrinivasan on 2/13/2017.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "contacts")
@Path("/")
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
    @Path("/contacts")
    @Produces("application/vnd.com.demo.contact-management.contact+xml;charset=UTF-8;version=1")
    public Response getAllContacts(@Context UriInfo ui) throws JsonProcessingException {
        MultivaluedMap<String, String> queryParameters = ui.getQueryParameters();
        Iterable<Contact> contacts = null;
        if(queryParameters.size()==0){
            contacts = contactRepository.findAll();
        }
        Contacts contactsWrapper  = new Contacts();
        contactsWrapper.getContacts().addAll(StreamSupport.stream(contacts.spliterator(),true).collect(Collectors.toList()));
          return Response.status(200).entity( contactsWrapper).build();
    }
    @GET
    @Path("/contacts/{id}")
    public Response findContact(@PathParam("id") Long id) throws JsonProcessingException {
          return Response.status(200).entity( new Gson().toJson(contactRepository.findOne(id))).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_XML)
    public ContactService getServiceInfo() {
        return new ContactService();
    }
}
