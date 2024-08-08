package com.Backend.java.demo.service;

import com.Backend.java.demo.payload.TasksDto;

import java.util.List;


public interface TaskService {
    public TasksDto saveTask(long userid, TasksDto taskDto);
    public List<TasksDto> showAllTask(long userid);
    public TasksDto individualId(long userid,long taskid);
    public void deletTask(long userid,long taskid);

}
