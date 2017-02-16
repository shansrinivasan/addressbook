package com.shan.demo.repository;

import com.shan.demo.model.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ssrinivasan on 2/13/2017.
 */
public interface  ContactRepository  extends CrudRepository<Contact, Long> {
    List<Contact> findByFirstNameContainingIgnoringCaseOrderByFirstName(String firstName);
    List<Contact> findByLastNameContainingIgnoringCaseOrderByFirstName(String lastName);
    List<Contact> findByFirstNameAndLastNameContainingIgnoringCaseOrderByFirstName(String firstName, String lastName);

}
