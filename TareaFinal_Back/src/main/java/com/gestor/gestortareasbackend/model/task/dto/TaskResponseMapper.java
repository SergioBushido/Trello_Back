package com.gestor.gestortareasbackend.model.task.dto;

import com.gestor.gestortareasbackend.model.tag.Tag;
import com.gestor.gestortareasbackend.model.task.Task;
import com.gestor.gestortareasbackend.model.tag.dto.TagResponseMapper;
import com.gestor.gestortareasbackend.model.tag.dto.ResponseTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = TagResponseMapper.class)
public interface TaskResponseMapper {

    TaskResponseMapper INSTANCE = Mappers.getMapper(TaskResponseMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "tags", source = "tags")
    ResponseTask taskToResponseTask(Task task);

    List<ResponseTask> tasksToResponseTasks(List<Task> tasks);

    void updateEntityFromDto(RequestTask dto, @MappingTarget Task entity);

    default Set<ResponseTag> mapTags(Set<Tag> tags) {
        if (tags == null) {
            return null;
        }
        return tags.stream()
                .map(tag -> ResponseTag.builder().id(tag.getId()).name(tag.getName()).build())
                .collect(Collectors.toSet());
    }
}
