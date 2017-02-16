package com.shan.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shan.demo.assembler.ContactResourceAssembler;
import com.shan.demo.assembler.RelationshipResourceAssembler;
import com.shan.demo.model.Category;
import com.shan.demo.model.Contact;
import com.shan.demo.model.Relationship;
import com.shan.demo.repository.ContactRepository;
import com.shan.demo.repository.RelationshipRepository;
import com.shan.demo.resources.ContactResource;
import com.shan.demo.resources.RelationshipResource;
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
@Path("/relationships")
@Service
public class RelationshipService {

    private String uri = "/relationships";

    @Autowired
    private RelationshipRepository relationshipRepository;

    public String getUri() {
        return uri;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRelationships(){
        RelationshipResourceAssembler assembler = new RelationshipResourceAssembler();
        List<RelationshipResource> resources = assembler.toResources(relationshipRepository.findAll());
        Resources<RelationshipResource> wrapped = new Resources<>(resources);
        wrapped.add(
                JaxRsLinkBuilder
                        .linkTo(RelationshipService.class)
                        .withSelfRel()
        );
        return Response.status(200).entity(wrapped).build();
    }
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRelationship(Relationship relationship)
            throws URISyntaxException {
        relationship.setUpdateDate(new Date());
        relationship.setCreateDate(new Date());
        return Response.status(201)
                .contentLocation(new URI("/relationship/"+relationshipRepository.save(relationship).getId())).build();
    }
}
