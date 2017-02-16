package com.shan.demo.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by ssrinivasan on 2/15/2017.
 */
public class AddressBookResource extends ResourceSupport {
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

