package com.gestor.gestortareasbackend.model.role.dto;

import com.gestor.gestortareasbackend.model.role.Role;
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
public class RoleResponseMapperImpl implements RoleResponseMapper {

    @Override
    public ResponseRole roleToResponseRole(Role role) {
        if ( role == null ) {
            return null;
        }

        ResponseRole.ResponseRoleBuilder responseRole = ResponseRole.builder();

        responseRole.id( role.getId() );
        responseRole.name( role.getName() );
        responseRole.permissions( mapPermissions( role.getPermissions() ) );

        return responseRole.build();
    }

    @Override
    public List<ResponseRole> rolesToResponseRoles(List<Role> roles) {
        if ( roles == null ) {
            return null;
        }

        List<ResponseRole> list = new ArrayList<ResponseRole>( roles.size() );
        for ( Role role : roles ) {
            list.add( roleToResponseRole( role ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(RequestRole dto, Role entity) {
        if ( dto == null ) {
            return;
        }

        entity.setName( dto.getName() );
    }
}
