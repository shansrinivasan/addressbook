package com.shan.demo.assembler;

import com.shan.demo.resources.AddressBookResource;
import com.shan.demo.services.AddressBookService;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by ssrinivasan on 2/15/2017.
 */
public class AddressBookResourceAssembler extends ResourceAssemblerSupport<String, AddressBookResource> {

    public AddressBookResourceAssembler() {
        super(AddressBookService.class, AddressBookResource.class);
    }

    @Override
    public AddressBookResource toResource(String uri) {
        AddressBookResource resource = createResourceWithId(
                "/"+uri,
                uri
        );
        resource.setUri(uri);
        return resource;
    }
}