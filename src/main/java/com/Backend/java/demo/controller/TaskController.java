package com.Backend.java.demo.controller;

import com.Backend.java.demo.payload.TasksDto;
import com.Backend.java.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/{userid}/tasks")
    public ResponseEntity<TasksDto> saveTask(@PathVariable(name = "userid") long userid, @RequestBody TasksDto taskDto) {
        return new ResponseEntity<>(taskService.saveTask(userid, taskDto), HttpStatus.CREATED);
    }
    @GetMapping("/{userid}/tasks")
    public ResponseEntity<List<TasksDto>> getTasks(@PathVariable(name="userid") long userid){
        return new ResponseEntity<>(taskService.showAllTask(userid),HttpStatus.OK);
    }
    @GetMapping("/{userid}/tasks/{taskid}")
    public ResponseEntity<TasksDto> getSingleTask(
            @PathVariable(name="userid") long userid,
            @PathVariable(name = "taskid") long taskid){
        return new ResponseEntity<>(taskService.individualId(userid, taskid),HttpStatus.OK);
    }
    @DeleteMapping("/{userid}/tasks/{taskid}")
    public ResponseEntity<String> DeleteTask(
            @PathVariable(name = "userid") long userid,
            @PathVariable(name = "taskid") long taskid
    ){
        taskService.deletTask(userid, taskid);
        return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }
}

