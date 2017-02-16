package com.shan.demo.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by ssrinivasan on 2/15/2017.
 */
public class CategoryResource extends ResourceSupport {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
}

