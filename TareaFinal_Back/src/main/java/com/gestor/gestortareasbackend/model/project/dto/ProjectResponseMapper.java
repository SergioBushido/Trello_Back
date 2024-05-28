package com.gestor.gestortareasbackend.model.project.dto;

import com.gestor.gestortareasbackend.model.project.Project;
import com.gestor.gestortareasbackend.model.task.dto.TaskMapper;
import com.gestor.gestortareasbackend.model.user.dto.UserResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", uses = {TaskMapper.class, UserResponseMapper.class})
public interface ProjectResponseMapper {

    ProjectResponseMapper INSTANCE = Mappers.getMapper(ProjectResponseMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "user", target = "member")
    @Mapping(source = "tasks", target = "tasks")
    ResponseProject entityToResponse(Project project);


    List<ResponseProject> entitiesToResponses(List<Project> projects);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "user.id", source = "memberId")
    void updateEntityFromDto(RequestProject dto, @MappingTarget Project entity);

}
