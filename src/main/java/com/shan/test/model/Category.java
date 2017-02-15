package com.shan.test.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Date createDate;
    private Date updateDate;

    @OneToMany
    private Set<Contact> contacts;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return String.format(
                "Category[id=%d, name='%s',description='%s', createDate='%s', createDate='%s', updateDate='%s'," +
                        " workPhone='%s', cellPhone='%s', fax='%s', email='%s']",
                id, name, description, createDate, createDate, updateDate);
    }

}