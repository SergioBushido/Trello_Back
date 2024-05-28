package com.gestor.gestortareasbackend.model.permission.dto;

import com.gestor.gestortareasbackend.model.permission.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionResponseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ResponsePermission entityToResponse(Permission permission);

    void updateEntityFromDto(RequestPermission requestPermission, @MappingTarget Permission entity);
    List<ResponsePermission> entitiesToResponses(List<Permission> permissions);

}