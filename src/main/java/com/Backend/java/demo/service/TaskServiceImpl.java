package com.Backend.java.demo.service;

import com.Backend.java.demo.Exception.ApiException;
import com.Backend.java.demo.Exception.IdTaskNotFound;
import com.Backend.java.demo.Exception.UserNotFound;
import com.Backend.java.demo.entity.Task;
import com.Backend.java.demo.entity.Users;
import com.Backend.java.demo.payload.TasksDto;
import com.Backend.java.demo.repository.TaskRepository;
import com.Backend.java.demo.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsersRepository userRepo;
    @Autowired
    private TaskRepository taskRepo;
    @Override
    public TasksDto saveTask(long userid, TasksDto taskDto) {
        Users user = userRepo.findById(userid).orElseThrow(
                () -> new UserNotFound(String.format("User with ID %d not found. Please provide a valid user ID.", userid))
        );

        Task task = modelMapper.map(taskDto, Task.class);
        task.setUsers(user);
        Task savedTask = taskRepo.save(task);
        return modelMapper.map(savedTask, TasksDto.class);
    }

    @Override
    public List<TasksDto> showAllTask(long userid) {
        userRepo.findById(userid).orElseThrow(
                () -> new IdTaskNotFound(String.format("User with ID %d not found. Please provide a valid user ID.", userid))
        );
        List<Task> tasksList = taskRepo.findAllByUserId(userid);
        return tasksList.stream().map(
                task -> modelMapper.map(task, TasksDto.class)
        ).collect(Collectors.toList());

    }

    @Override
    public TasksDto individualId(long userid, long taskid) {
        Users user=userRepo.findById(userid).orElseThrow(
                ()->new UserNotFound(String.format("user with id %d not found",userid))
        );
        Task task=taskRepo.findById(taskid).orElseThrow(
                ()->new IdTaskNotFound(String.format("user with this task %d not found",taskid))
        );
        if(user.getId() !=task.getUsers().getId()){
            throw new ApiException(String.format("cant get other user %d tasker",taskid));
        }
        return modelMapper.map(task,TasksDto.class);
    }

    @Override
    public void deletTask(long userid, long taskid) {
        Users user=userRepo.findById(userid).orElseThrow(
                ()->new UserNotFound(String.format("task %d not exist to delete",taskid))
        );
        Task task=taskRepo.findById(taskid).orElseThrow(
                ()->new IdTaskNotFound(String.format("task %d not found in %d",taskid,userid))
        );
        if(user.getId() !=task.getUsers().getId()){
            throw new ApiException(String.format("task %d not found in %d",taskid,userid));
        }
        taskRepo.deleteById(taskid);
    }
}
