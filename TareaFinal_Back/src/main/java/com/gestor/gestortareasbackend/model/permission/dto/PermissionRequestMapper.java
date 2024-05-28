package com.gestor.gestortareasbackend.model.permission.dto;

import com.gestor.gestortareasbackend.model.permission.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PermissionRequestMapper {


    @Mapping(target = "name", source = "name")
    Permission dtoToEntity(RequestPermission requestPermission);
}
