package com.company.events.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|student")
@Table(name = "EVENTS_PARTICIPANT")
@Entity(name = "events$Participant")
public class Participant extends StandardEntity {
    private static final long serialVersionUID = -5860015332082561060L;

    @Column(name = "ROLE", length = 150)
    protected String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    protected Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID")
    protected Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }


    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }


}