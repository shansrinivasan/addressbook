package com.shan.demo.assembler;

import com.shan.demo.model.Relationship;
import com.shan.demo.resources.RelationshipResource;
import com.shan.demo.services.RelationshipService;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by ssrinivasan on 2/15/2017.
 */
public class RelationshipResourceAssembler extends ResourceAssemblerSupport<Relationship, RelationshipResource> {

    public RelationshipResourceAssembler() {
        super(RelationshipService.class, RelationshipResource.class);
    }

    @Override
    public RelationshipResource toResource(Relationship entity) {
        RelationshipResource resource = createResourceWithId(
                "contacts/"+entity.getSource().getId()+"/relationship/"+entity.getTarget().getId(),
                entity
        );
        resource.setSourcefullName(entity.getSource().getFirstName()+" "+entity.getSource().getLastName());
        resource.setRelationshipType(entity.getType());
        resource.setTargetfullName(entity.getTarget().getFirstName()+" "+entity.getTarget().getLastName());
        return resource;
    }
}