package com.gestor.gestortareasbackend.model.task.dto;

import com.gestor.gestortareasbackend.model.task.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Named("responseTaskSetToTaskSet")
    default Set<Task> mapResponseTaskSetToTaskSet(Set<ResponseTask> tasks) {
        return tasks.stream()
                .map(this::mapResponseTaskToTask)
                .collect(Collectors.toSet());
    }

    ResponseTask taskToResponseTask(Task task);

    Task mapResponseTaskToTask(ResponseTask responseTask);
}

