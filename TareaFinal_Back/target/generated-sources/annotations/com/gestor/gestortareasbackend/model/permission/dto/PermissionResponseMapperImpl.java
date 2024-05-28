package com.gestor.gestortareasbackend.model.permission.dto;

import com.gestor.gestortareasbackend.model.permission.Permission;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-28T13:28:57+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class PermissionResponseMapperImpl implements PermissionResponseMapper {

    @Override
    public ResponsePermission entityToResponse(Permission permission) {
        if ( permission == null ) {
            return null;
        }

        ResponsePermission.ResponsePermissionBuilder responsePermission = ResponsePermission.builder();

        responsePermission.id( permission.getId() );
        responsePermission.name( permission.getName() );

        return responsePermission.build();
    }

    @Override
    public void updateEntityFromDto(RequestPermission requestPermission, Permission entity) {
        if ( requestPermission == null ) {
            return;
        }

        entity.setName( requestPermission.getName() );
    }

    @Override
    public List<ResponsePermission> entitiesToResponses(List<Permission> permissions) {
        if ( permissions == null ) {
            return null;
        }

        List<ResponsePermission> list = new ArrayList<ResponsePermission>( permissions.size() );
        for ( Permission permission : permissions ) {
            list.add( entityToResponse( permission ) );
        }

        return list;
    }
}
