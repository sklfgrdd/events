package com.company.events.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NamePattern("%s|name")
@Table(name = "EVENTS_EVENT")
@Entity(name = "events$Event")
public class Event extends StandardEntity {
    private static final long serialVersionUID = 4663038227316350061L;

    @Column(name = "NAME")
    protected String name;

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE")
    protected Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE")
    protected Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHIEF_ID")
    protected Student chief;

    @Column(name = "LOCATION")
    protected String location;

    @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST)
    protected List<Task> tasks;

    @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST)
    protected List<Participant> participants;

   /* @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST)
    protected List<Logistics> Logistics;*/

    @Column(name = "CABINET", length = 50)
    protected String cabinet;

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String getCabinet() {
        return cabinet;
    }


  /*  public void setLogistics(List<Logistics> Logistics) {
        this.Logistics = Logistics;
    }

    public List<Logistics> getLogistics() {
        return Logistics;
    }*/


    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Participant> getParticipants() {
        return participants;
    }


    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setChief(Student chief) {
        this.chief = chief;
    }

    public Student getChief() {
        return chief;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }


}