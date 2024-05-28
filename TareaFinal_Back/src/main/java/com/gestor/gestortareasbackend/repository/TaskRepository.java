package com.gestor.gestortareasbackend.repository;

import com.gestor.gestortareasbackend.model.permission.Permission;
import com.gestor.gestortareasbackend.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByNameContainingIgnoreCase(String name);
}