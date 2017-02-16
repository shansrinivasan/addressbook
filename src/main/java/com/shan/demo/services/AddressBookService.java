package com.shan.demo.services;

import com.shan.demo.assembler.AddressBookResourceAssembler;
import com.shan.demo.resources.AddressBookResource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ssrinivasan on 2/16/2017.
 */
@Path("/addressbook-management")
@Service
public class AddressBookService {
    private String[] uris = {"contacts","categories","relationships","tags"};

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAddressbookOperations(){
        AddressBookResourceAssembler assembler = new AddressBookResourceAssembler();
        List<AddressBookResource> resources = assembler.toResources(Arrays.asList(uris));
        Resources<AddressBookResource> wrapped = new Resources<>(resources);
        wrapped.add(
                JaxRsLinkBuilder
                        .linkTo(AddressBookService.class)
                        .withSelfRel()
        );
        return Response.status(200).entity(wrapped).build();
    }
}
