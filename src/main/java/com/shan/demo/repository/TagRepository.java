package com.shan.demo.repository;

import com.shan.demo.model.Category;
import com.shan.demo.model.Contact;
import com.shan.demo.model.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ssrinivasan on 2/15/2017.
 */
public interface TagRepository extends CrudRepository<Tag, String> {
    List<Tag> findAllByOrderByNameAsc();

}
