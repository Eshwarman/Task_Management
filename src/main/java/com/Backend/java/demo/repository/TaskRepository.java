package com.Backend.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Backend.java.demo.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long>{
    List<Task> findAllByUserId(long userid);
}
