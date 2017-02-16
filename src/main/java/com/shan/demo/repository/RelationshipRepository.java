package com.shan.demo.repository;

import com.shan.demo.model.Contact;
import com.shan.demo.model.Relationship;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ssrinivasan on 2/15/2017.
 */
public interface RelationshipRepository extends CrudRepository<Relationship, Long> {
    List<Relationship> findBySourceIdAndTargetId(Long sourceId,Long targetId);
}
