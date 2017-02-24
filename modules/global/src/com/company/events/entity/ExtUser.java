package com.company.events.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

@Entity(name = "events$ExtUser")
public class ExtUser extends User {
    private static final long serialVersionUID = -74870332728360683L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID")
    protected Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }


}