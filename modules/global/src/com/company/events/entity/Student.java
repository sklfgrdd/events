package com.company.events.entity;

import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "EVENTS_STUDENT")
@NamePattern(" |lastName,firstName")
@Entity(name = "events$Student")
public class Student extends StandardEntity {
    private static final long serialVersionUID = 4361144725940240964L;

    @Column(name = "UNIVERSITY_GROUP", length = 10)
    protected String universityGroup;

    @Column(name = "PHONE", length = 11)
    protected String phone;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY")
    protected Date birthday;

    @Column(name = "POST", length = 200)
    protected String post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    protected Department department;

    @Column(name = "FIRST_NAME")
    protected String firstName;

    @Column(name = "LAST_NAME")
    protected String lastName;

    @Column(name = "MIDDLE_NAME")
    protected String middleName;

    @OnDelete(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    protected User user;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }


    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }


    public void setUniversityGroup(String universityGroup) {
        this.universityGroup = universityGroup;
    }

    public String getUniversityGroup() {
        return universityGroup;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPost() {
        return post;
    }


}