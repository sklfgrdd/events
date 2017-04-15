package com.company.events.entity;

import javax.persistence.*;
import java.util.Date;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.core.entity.StandardEntity;
import java.util.List;

@NamePattern("%s %s|lastName,firstName")
@Table(name = "EVENTS_STUDENT")
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

    @JoinTable(name = "EVENTS_TASK_STUDENT_LINK",
        joinColumns = @JoinColumn(name = "STUDENT_ID"),
        inverseJoinColumns = @JoinColumn(name = "TASK_ID"))
    @ManyToMany
    protected List<Task> tasks;

    @MetaProperty @Transient
    protected String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    protected ExtUser user;

    public void setUser(ExtUser user) {
        this.user = user;
    }

    public ExtUser getUser() {
        return user;
    }


    public String getName() {
        String s = lastName + " " + firstName.charAt(0) + ".";
        return s;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

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