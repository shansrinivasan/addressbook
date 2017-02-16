package com.shan.demo.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by ssrinivasan on 2/15/2017.
 */
public class TagResource extends ResourceSupport {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

