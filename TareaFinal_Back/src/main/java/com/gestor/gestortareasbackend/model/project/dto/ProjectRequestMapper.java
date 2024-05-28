package com.gestor.gestortareasbackend.model.project.dto;

import com.gestor.gestortareasbackend.model.project.Project;
import com.gestor.gestortareasbackend.model.task.dto.TaskMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface ProjectRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "user.id", source = "memberId")
    @Mapping(target = "user.username", ignore = true)
    @Mapping(target = "user.email", ignore = true)
    Project dtoToEntity(RequestProject requestProject);

}
