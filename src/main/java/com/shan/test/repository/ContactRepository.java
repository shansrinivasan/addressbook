package com.shan.test.repository;

import com.shan.test.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ssrinivasan on 2/13/2017.
 */
public interface  ContactRepository  extends CrudRepository<Contact, Long> {
    List<Contact> findByLastName(String lastName);
    List<Contact> findByFirstName(String firstName);
}
