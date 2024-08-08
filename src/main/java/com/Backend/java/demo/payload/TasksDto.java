package com.Backend.java.demo.payload;

public class TasksDto {
    private long id;
    private String taskName;

    public TasksDto() {
    }

    @Override
    public String toString() {
        return "TasksDto{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
