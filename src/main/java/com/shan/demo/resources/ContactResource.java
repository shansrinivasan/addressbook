package com.shan.demo.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by ssrinivasan on 2/15/2017.
 */
public class ContactResource extends ResourceSupport {
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
}

