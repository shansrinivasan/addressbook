package com.shan.demo.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by ssrinivasan on 2/15/2017.
 */
public class RelationshipResource extends ResourceSupport {
    private String sourcefullName;
    private String targetfullName;
    private String relationshipType;

    public String getSourcefullName() {
        return sourcefullName;
    }

    public void setSourcefullName(String sourcefullName) {
        this.sourcefullName = sourcefullName;
    }

    public String getTargetfullName() {
        return targetfullName;
    }

    public void setTargetfullName(String targetfullName) {
        this.targetfullName = targetfullName;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

}

