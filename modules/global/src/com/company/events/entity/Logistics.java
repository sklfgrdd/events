package com.company.events.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Table(name = "EVENTS_LOGISTICS")
@Entity(name = "events$Logistics")
public class Logistics extends StandardEntity {
    private static final long serialVersionUID = -7690925662746117915L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "AVAILABLE")
    protected Boolean available;

    @Column(name = "CHIEF")
    protected String chief;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    protected Event event;

    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

    public String getChief() {
        return chief;
    }
}