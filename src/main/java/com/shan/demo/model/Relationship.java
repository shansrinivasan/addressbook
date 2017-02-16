package com.shan.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Relationship {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String type;
    private String description;
    private Date createDate;
    private Date updateDate;

    @ManyToOne
    private Contact source;

    @ManyToOne
    private Contact target;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Contact getSource() {
        return source;
    }

    public void setSource(Contact source) {
        this.source = source;
    }

    public Contact getTarget() {
        return target;
    }

    public void setTarget(Contact target) {
        this.target = target;
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
                "Category[id=%d, shortDesc='%s',longDesc='%s', createDate='%s', createDate='%s', updateDate='%s'," +
                        " workPhone='%s', cellPhone='%s', fax='%s', email='%s']",
                id, createDate, createDate, updateDate);
    }

}