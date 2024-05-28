package com.gestor.gestortareasbackend.services;

import com.gestor.gestortareasbackend.model.project.Project;
import com.gestor.gestortareasbackend.model.tag.Tag;
import com.gestor.gestortareasbackend.model.task.Task;
import com.gestor.gestortareasbackend.model.task.dto.RequestTask;
import com.gestor.gestortareasbackend.model.task.dto.ResponseTask;
import com.gestor.gestortareasbackend.model.task.dto.TaskResponseMapper;
import com.gestor.gestortareasbackend.repository.ProjectRepository;
import com.gestor.gestortareasbackend.repository.TagRepository;
import com.gestor.gestortareasbackend.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;
    private final TaskResponseMapper taskResponseMapper;

    @Transactional(readOnly = true)
    public Optional<ResponseTask> getTaskById(Long id) {
        final Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.map(taskResponseMapper::taskToResponseTask);
    }

    @Transactional(readOnly = true)
    public List<ResponseTask> getAllTasks() {
        return taskResponseMapper.tasksToResponseTasks(taskRepository.findAll());
    }

    @Transactional
    public ResponseTask createTask(RequestTask requestTask) {
        Task task = Task.builder()
                .name(requestTask.getName())
                .status(requestTask.getStatus())
                .project(findProjectById(requestTask.getProjectId()).orElseThrow(
                        () -> new EntityNotFoundException("Project not found with id: " + requestTask.getProjectId())))
                .tags(findTagsByIds(requestTask.getTagIds()))
                .description(requestTask.getDescription())
                .build();

        return taskResponseMapper.taskToResponseTask(taskRepository.save(task));
    }

    private Optional<Project> findProjectById(final Long projectId) {
        return projectRepository.findById(projectId);
    }

    private Set<Tag> findTagsByIds(Set<Long> tagIds) {
        if (tagIds == null || tagIds.isEmpty()) {
            return new HashSet<>();
        }
        return new HashSet<>(tagRepository.findAllById(tagIds));
    }

    @Transactional
    public Optional<ResponseTask> updateTask(Long id, RequestTask taskDetails) {
        final Optional<Task> existingTask = taskRepository.findById(id);
        existingTask.ifPresent(task -> {
            taskResponseMapper.updateEntityFromDto(taskDetails, task);
            taskRepository.save(task);
        });
        return existingTask.map(taskResponseMapper::taskToResponseTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public boolean existsById(final Long id) {
        return taskRepository.existsById(id);
    }

    public List<ResponseTask> findTasksByName(final String name) {
        final List<Task> tasks = taskRepository.findByNameContainingIgnoreCase(name);
        return taskResponseMapper.tasksToResponseTasks(tasks);
    }

}
