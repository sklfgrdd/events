package com.company.events.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

/**
 * Created by Kate on 16.04.2017.
 */
@Table(name = "EVENTS_TASK_STUDENT_LINK")
@Entity(name = "events$TaskStudentLink")
public class TaskStudentLink extends StandardEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TASK_ID")
    protected Student task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID")
    protected Student student;

    public Student getTask() {
        return task;
    }

    public void setTask(Student task) {
        this.task = task;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
