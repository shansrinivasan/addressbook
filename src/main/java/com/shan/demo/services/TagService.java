package com.shan.demo.services;

import com.shan.demo.assembler.CategoryResourceAssembler;
import com.shan.demo.assembler.TagResourceAssembler;
import com.shan.demo.model.Category;
import com.shan.demo.model.Contact;
import com.shan.demo.model.Tag;
import com.shan.demo.repository.CategoryRepository;
import com.shan.demo.repository.ContactRepository;
import com.shan.demo.repository.TagRepository;
import com.shan.demo.resources.CategoryResource;
import com.shan.demo.resources.TagResource;
import org.jboss.resteasy.annotations.providers.jackson.Formatted;
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
@Path("/tags")
@Service
public class TagService {

    private String uri = "/tags";

    @Autowired
    private TagRepository tagRepository;


    public String getUri() {
        return uri;
    }

    @GET
    @Path("/{tagName}")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response findCategory(@PathParam("tagName") String tagName) {
        return Response.status(200).entity( tagRepository.findOne(tagName)).build();
    }

    @GET
    @Path("/")
    @Formatted
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTags(){
        TagResourceAssembler assembler = new TagResourceAssembler();
        List<TagResource> resources = assembler.toResources(tagRepository.findAllByOrderByNameAsc());
        Resources<TagResource> wrapped = new Resources<>(resources);
        wrapped.add(
                JaxRsLinkBuilder
                        .linkTo(TagService.class)
                        .withSelfRel()
        );
        return Response.status(200).entity(wrapped).build();
    }
 }
