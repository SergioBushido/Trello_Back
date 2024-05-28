package com.gestor.gestortareasbackend.model.role.dto;

import com.gestor.gestortareasbackend.model.permission.Permission;
import com.gestor.gestortareasbackend.model.permission.dto.PermissionResponseMapper;
import com.gestor.gestortareasbackend.model.permission.dto.ResponsePermission;
import com.gestor.gestortareasbackend.model.role.Role;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = PermissionResponseMapper.class)
public interface RoleResponseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "permissions", source = "permissions")
    ResponseRole roleToResponseRole(Role role);

    default Set<ResponsePermission> mapPermissions(Set<Permission> permissions) {
        if (permissions == null) {
            return null;
        }
        return permissions.stream()
                .map(permission -> new ResponsePermission(permission.getId(), permission.getName()))
                .collect(Collectors.toSet());
    }

    List<ResponseRole> rolesToResponseRoles(List<Role> roles);

    void updateEntityFromDto(RequestRole dto, @MappingTarget Role entity);
}

