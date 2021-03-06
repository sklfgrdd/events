package com.company.events.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import org.apache.commons.lang.StringUtils;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

@Table(name = "EVENTS_TASK")
@Entity(name = "events$Task")
public class Task extends StandardEntity {
    private static final long serialVersionUID = 5220074777926377265L;

    @Column(name = "NAME")
    protected String name;

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "DEADLINE")
    protected Date deadline;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "EVENT_ID")
    protected Event event;

    @JoinTable(name = "EVENTS_TASK_STUDENT_LINK",
        joinColumns = @JoinColumn(name = "TASK_ID"),
        inverseJoinColumns = @JoinColumn(name = "STUDENT_ID"))
    @ManyToMany
    protected List<Student> executors;

    public void setExecutors(List<Student> executors) {
        this.executors = executors;
    }

    public List<Student> getExecutors() {
        return executors;
    }

    public String getExecutorsList() {
        List<String> studentList = new ArrayList<>();
        for (Student student: executors)
            studentList.add(student.getName());
        return StringUtils.join(studentList, ",");
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

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}