package com.gestor.gestortareasbackend.model.project.dto;

import com.gestor.gestortareasbackend.model.project.Project;
import com.gestor.gestortareasbackend.model.task.Task;
import com.gestor.gestortareasbackend.model.user.User;
import com.gestor.gestortareasbackend.model.user.dto.UserResponseMapper;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-28T13:28:57+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ProjectResponseMapperImpl implements ProjectResponseMapper {

    @Autowired
    private UserResponseMapper userResponseMapper;

    @Override
    public ResponseProject entityToResponse(Project project) {
        if ( project == null ) {
            return null;
        }

        ResponseProject.ResponseProjectBuilder responseProject = ResponseProject.builder();

        responseProject.id( project.getId() );
        responseProject.name( project.getName() );
        responseProject.member( userResponseMapper.userToResponseUser( project.getUser() ) );
        responseProject.tasks( taskSetToResponseProjectTaskSet( project.getTasks() ) );

        return responseProject.build();
    }

    @Override
    public List<ResponseProject> entitiesToResponses(List<Project> projects) {
        if ( projects == null ) {
            return null;
        }

        List<ResponseProject> list = new ArrayList<ResponseProject>( projects.size() );
        for ( Project project : projects ) {
            list.add( entityToResponse( project ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(RequestProject dto, Project entity) {
        if ( dto == null ) {
            return;
        }

        if ( entity.getUser() == null ) {
            entity.setUser( User.builder().build() );
        }
        requestProjectToUser( dto, entity.getUser() );
        entity.setName( dto.getName() );
    }

    protected ResponseProjectTask taskToResponseProjectTask(Task task) {
        if ( task == null ) {
            return null;
        }

        ResponseProjectTask.ResponseProjectTaskBuilder responseProjectTask = ResponseProjectTask.builder();

        responseProjectTask.id( task.getId() );
        responseProjectTask.name( task.getName() );

        return responseProjectTask.build();
    }

    protected Set<ResponseProjectTask> taskSetToResponseProjectTaskSet(Set<Task> set) {
        if ( set == null ) {
            return null;
        }

        Set<ResponseProjectTask> set1 = new LinkedHashSet<ResponseProjectTask>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Task task : set ) {
            set1.add( taskToResponseProjectTask( task ) );
        }

        return set1;
    }

    protected void requestProjectToUser(RequestProject requestProject, User mappingTarget) {
        if ( requestProject == null ) {
            return;
        }

        mappingTarget.setId( requestProject.getMemberId() );
    }
}
