package com.gestor.gestortareasbackend.model.permission.dto;

import com.gestor.gestortareasbackend.model.permission.Permission;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-28T13:28:56+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class PermissionRequestMapperImpl implements PermissionRequestMapper {

    @Override
    public Permission dtoToEntity(RequestPermission requestPermission) {
        if ( requestPermission == null ) {
            return null;
        }

        Permission permission = new Permission();

        permission.setName( requestPermission.getName() );

        return permission;
    }
}
