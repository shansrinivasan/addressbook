package com.shan.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shan.demo.assembler.CategoryResourceAssembler;
import com.shan.demo.assembler.RelationshipResourceAssembler;
import com.shan.demo.model.Category;
import com.shan.demo.model.Contact;
import com.shan.demo.repository.CategoryRepository;
import com.shan.demo.repository.ContactRepository;
import com.shan.demo.repository.RelationshipRepository;
import com.shan.demo.resources.CategoryResource;
import com.shan.demo.resources.RelationshipResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

/**
 * Created by ssrinivasan on 2/13/2017.
 */
@Path("/categories")
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ContactRepository contactRepository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCategory(@PathParam("id") Long id) {
        return Response.status(200).entity( categoryRepository.findOne(id)).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories(){
        CategoryResourceAssembler assembler = new CategoryResourceAssembler();
        List<CategoryResource> resources = assembler.toResources(categoryRepository.findAll());
        Resources<CategoryResource> wrapped = new Resources<>(resources);
        wrapped.add(
                JaxRsLinkBuilder
                        .linkTo(CategoryService.class)
                        .withSelfRel()
        );
        return Response.status(200).entity(wrapped).build();
    }
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategory(Category category)
            throws URISyntaxException {
        category.setUpdateDate(new Date());
        category.setCreateDate(new Date());
        return Response.status(201)
                .contentLocation(new URI("/categories/"+categoryRepository.save(category).getId())).build();
    }
    @POST
    @Path("/{categoryid}/contacts/{contactId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addContact(@PathParam("categoryid") Long categoryid, @PathParam("contactId") Long contactId)
            throws URISyntaxException {
        Category category = categoryRepository.findOne(categoryid);
        Contact contact = contactRepository.findOne(contactId);
        category.getContacts().add(contact);
        category.setUpdateDate(new Date());
        return Response.status(201)
                .contentLocation(new URI("/categories/"+categoryRepository.save(category).getId())).build();
    }
}
