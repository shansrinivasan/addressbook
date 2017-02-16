package com.shan.demo.assembler;

import com.shan.demo.model.Relationship;
import com.shan.demo.model.Tag;
import com.shan.demo.resources.RelationshipResource;
import com.shan.demo.resources.TagResource;
import com.shan.demo.services.RelationshipService;
import com.shan.demo.services.TagService;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by ssrinivasan on 2/15/2017.
 */
public class TagResourceAssembler extends ResourceAssemblerSupport<Tag, TagResource> {

    public TagResourceAssembler() {
        super(TagService.class, TagResource.class);
    }

    @Override
    public TagResource toResource(Tag entity) {
        TagResource resource = createResourceWithId(
                "tags/"+entity.getName(),
                entity
        );
        resource.setName(entity.getName());
        return resource;
    }
}