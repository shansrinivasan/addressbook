package com.shan.demo.assembler;

import com.shan.demo.model.Category;
import com.shan.demo.model.Relationship;
import com.shan.demo.resources.CategoryResource;
import com.shan.demo.resources.RelationshipResource;
import com.shan.demo.services.RelationshipService;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by ssrinivasan on 2/15/2017.
 */
public class CategoryResourceAssembler extends ResourceAssemblerSupport<Category, CategoryResource> {

    public CategoryResourceAssembler() {
        super(RelationshipService.class, CategoryResource.class);
    }

    @Override
    public CategoryResource toResource(Category entity) {
        CategoryResource resource = createResourceWithId(
                "categories/"+entity.getId(),
                entity
        );
        resource.setName(entity.getName());
        return resource;
    }
}