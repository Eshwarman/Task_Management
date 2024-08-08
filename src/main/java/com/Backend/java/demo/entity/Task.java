package com.Backend.java.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "taskname",nullable = false)
    private String taskName;
    public Task() {
    }

    public Task(long id, String taskname, com.Backend.java.demo.entity.Users user) {
        this.id = id;
        this.taskName = taskname;
           this.user = user;
    }

    public Users getUsers() {
        return user;
    }

    public void setUsers(Users users) {
        user = users;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="users_id")
    private Users user;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
