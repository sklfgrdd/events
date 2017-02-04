package com.company.events.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "EVENTS_DEPARTMENT")
@Entity(name = "events$Department")
public class Department extends StandardEntity {
    private static final long serialVersionUID = -8287891315946120858L;

    @Column(name = "NAME", length = 200)
    protected String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHIEF_ID")
    protected Student chief;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setChief(Student chief) {
        this.chief = chief;
    }

    public Student getChief() {
        return chief;
    }


}