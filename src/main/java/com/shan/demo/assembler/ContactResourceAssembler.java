package com.shan.demo.assembler;

import com.shan.demo.model.Contact;
import com.shan.demo.resources.ContactResource;
import com.shan.demo.services.ContactService;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by ssrinivasan on 2/15/2017.
 */
public class ContactResourceAssembler extends ResourceAssemblerSupport<Contact, ContactResource> {

    public ContactResourceAssembler() {
        super(ContactService.class, ContactResource.class);
    }

    @Override
    public ContactResource toResource(Contact entity) {
        ContactResource resource = createResourceWithId(
                "contacts/"+entity.getId(),
                entity
        );
        resource.setEmail(entity.getEmail());
        resource.setFullName(entity.getFirstName() + " " + entity.getLastName());
        return resource;
    }
}