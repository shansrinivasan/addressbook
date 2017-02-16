package com.shan.demo.services;

import com.shan.demo.assembler.ContactResourceAssembler;
import com.shan.demo.model.Contact;
import com.shan.demo.model.Relationship;
import com.shan.demo.model.Tag;
import com.shan.demo.repository.ContactRepository;
import com.shan.demo.repository.RelationshipRepository;
import com.shan.demo.repository.TagRepository;
import com.shan.demo.resources.ContactResource;
import org.jboss.resteasy.annotations.providers.jackson.Formatted;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by ssrinivasan on 2/13/2017.
 */
@Path("/contacts")
@Service
public class ContactService {

    private String uri = "/contacts";

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private RelationshipRepository relationshipRepository;

    @Autowired
    private TagRepository tagRepository;

    public String getUri() {
        return uri;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getAllContacts(@QueryParam("firstName") String firstName,@QueryParam("lastName") String lastName){
        List<Contact> contacts = new ArrayList<>();

        /**
         * Below Conditions and Search can be sophisticated based on User Experience requirements.
          */

        //Both firstName and lastName empty
        if(StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName)){
            contacts = StreamSupport.stream(contactRepository.findAll().spliterator(),true).collect(Collectors.toList());
        } else
        //Both are present send only names containing the specified first and last name combo
        if(!StringUtils.isEmpty(firstName) && !StringUtils.isEmpty(lastName)) {
            contacts = contactRepository.findByFirstNameAndLastNameContainingIgnoringCaseOrderByFirstName(firstName, lastName);
        } else
        //Only FirstName is present
        if(!StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName)){
            contacts = contactRepository.findByFirstNameContainingIgnoringCaseOrderByFirstName(firstName);
        } else
        //Only LastName is present
        if(!StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName)) {
            contacts = contactRepository.findByLastNameContainingIgnoringCaseOrderByFirstName(firstName);
        }
        ContactResourceAssembler assembler = new ContactResourceAssembler();
        List<ContactResource> resources = assembler.toResources(contacts);
        Resources<ContactResource> wrapped = new Resources<>(resources);
        wrapped.add(
                JaxRsLinkBuilder
                        .linkTo(ContactService.class)
                        .withSelfRel()
        );
        return Response.status(200).entity(wrapped).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response findContact(@PathParam("id") Long id){
          return Response.status(200).entity( contactRepository.findOne(id)).build();
    }

    @POST
    @Path("/{id}/tags/{tagName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tagContact(@PathParam("id") Long id, @PathParam("tagName") String tagName){
        Contact contact = contactRepository.findOne(id);
        Tag tag = null;
        if(tagRepository.exists(tagName)) {
            tag = tagRepository.findOne(tagName);
        } else {
            tag = new Tag();
            tag.setName(tagName);
            tag.setCreateDate(new Date());
        }
        tag.setUpdateDate(new Date());
        tag.getContacts().add(contact);
        tagRepository.save(tag);
        contact.setUpdateDate(new Date());
        contactRepository.save(contact);
        return Response.status(200).entity(contact).build();
    }
    @GET
    @Path("/{sourceContactId}/relationship/{targetContactid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRelationship(@PathParam("sourceContactId") Long sourceContactId, @PathParam("targetContactid") Long targetContactid){
        List<Relationship> relationship = relationshipRepository.findBySourceIdAndTargetId(sourceContactId,targetContactid);
        return Response.status(200).entity(relationship).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createContact(Contact contact)
            throws URISyntaxException {
                contact.setUpdateDate(new Date());
                contact.setCreateDate(new Date());
                contactRepository.save(contact);
        return Response.status(201)
                .contentLocation(new URI("/addressbook/contact/")).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateContact(@PathParam("id") Long id, Contact contact)
            throws URISyntaxException {
        if(!contactRepository.exists(id)){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        contact.setId(id);
        contact.setUpdateDate(new Date());
        contactRepository.save(contact);
        return Response.status(201)
                .contentLocation(new URI("/addressbook/contacts/"+id)).build();
    }

    //TODO PATCH is not supported with this version. so using PUT
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateContactAttributes(@PathParam("id") Long id, @Context UriInfo ui)
            throws URISyntaxException {
        if(!contactRepository.exists(id)){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        //Gets all query parameters and populates to the contact object.
        MultivaluedMap<String, String> queryParameters = ui.getQueryParameters();
        Contact contact = contactRepository.findOne(id);
        BeanUtils.copyProperties(queryParameters,contact);
        contact.setUpdateDate(new Date());
        contactRepository.save(contact);
        return Response.status(201)
                .contentLocation(new URI("/addressbook/contacts/"+id)).build();
    }

}
