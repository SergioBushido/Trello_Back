package com.gestor.gestortareasbackend.model.project.dto;

import com.gestor.gestortareasbackend.model.project.Project;
import com.gestor.gestortareasbackend.model.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-28T13:28:57+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ProjectRequestMapperImpl implements ProjectRequestMapper {

    @Override
    public Project dtoToEntity(RequestProject requestProject) {
        if ( requestProject == null ) {
            return null;
        }

        Project project = new Project();

        project.setUser( requestProjectToUser( requestProject ) );
        project.setName( requestProject.getName() );

        return project;
    }

    protected User requestProjectToUser(RequestProject requestProject) {
        if ( requestProject == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( requestProject.getMemberId() );

        return user.build();
    }
}
