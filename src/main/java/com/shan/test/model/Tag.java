package com.shan.test.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private Date createDate;
    private Date updateDate;

    @OneToMany
    private Set<Contact> contacts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return String.format(
                "Category[id=%d, name='%s',, createDate='%s', createDate='%s', updateDate='%s']",
                id, name, createDate, createDate, updateDate);
    }

}