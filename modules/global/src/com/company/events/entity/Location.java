package com.company.events.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s %s|name,address")
@Table(name = "EVENTS_LOCATION")
@Entity(name = "events$Location")
public class Location extends StandardEntity {
    private static final long serialVersionUID = -7961978492262291437L;

    @Column(name = "ADDRESS", length = 300)
    protected String address;

    @Column(name = "PHONE", length = 20)
    protected String phone;

    @Column(name = "NAME")
    protected String name;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }


}