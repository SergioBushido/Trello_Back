package com.gestor.gestortareasbackend.model.task.dto;

import com.gestor.gestortareasbackend.model.project.Project;
import com.gestor.gestortareasbackend.model.project.dto.ResponseProjectTask;
import com.gestor.gestortareasbackend.model.tag.Tag;
import com.gestor.gestortareasbackend.model.tag.dto.ResponseTag;
import com.gestor.gestortareasbackend.model.task.Task;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-28T13:28:57+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public ResponseTask taskToResponseTask(Task task) {
        if ( task == null ) {
            return null;
        }

        ResponseTask.ResponseTaskBuilder responseTask = ResponseTask.builder();

        responseTask.id( task.getId() );
        responseTask.name( task.getName() );
        responseTask.status( task.getStatus() );
        responseTask.project( projectToResponseProjectTask( task.getProject() ) );
        responseTask.tags( tagSetToResponseTagSet( task.getTags() ) );
        responseTask.description( task.getDescription() );

        return responseTask.build();
    }

    @Override
    public Task mapResponseTaskToTask(ResponseTask responseTask) {
        if ( responseTask == null ) {
            return null;
        }

        Task.TaskBuilder task = Task.builder();

        task.id( responseTask.getId() );
        task.name( responseTask.getName() );
        task.status( responseTask.getStatus() );
        task.description( responseTask.getDescription() );
        task.project( responseProjectTaskToProject( responseTask.getProject() ) );
        task.tags( responseTagSetToTagSet( responseTask.getTags() ) );

        return task.build();
    }

    protected ResponseProjectTask projectToResponseProjectTask(Project project) {
        if ( project == null ) {
            return null;
        }

        ResponseProjectTask.ResponseProjectTaskBuilder responseProjectTask = ResponseProjectTask.builder();

        responseProjectTask.id( project.getId() );
        responseProjectTask.name( project.getName() );

        return responseProjectTask.build();
    }

    protected ResponseTag tagToResponseTag(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        ResponseTag.ResponseTagBuilder responseTag = ResponseTag.builder();

        responseTag.id( tag.getId() );
        responseTag.name( tag.getName() );

        return responseTag.build();
    }

    protected Set<ResponseTag> tagSetToResponseTagSet(Set<Tag> set) {
        if ( set == null ) {
            return null;
        }

        Set<ResponseTag> set1 = new LinkedHashSet<ResponseTag>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Tag tag : set ) {
            set1.add( tagToResponseTag( tag ) );
        }

        return set1;
    }

    protected Project responseProjectTaskToProject(ResponseProjectTask responseProjectTask) {
        if ( responseProjectTask == null ) {
            return null;
        }

        Project project = new Project();

        project.setId( responseProjectTask.getId() );
        project.setName( responseProjectTask.getName() );

        return project;
    }

    protected Tag responseTagToTag(ResponseTag responseTag) {
        if ( responseTag == null ) {
            return null;
        }

        Tag.TagBuilder tag = Tag.builder();

        tag.id( responseTag.getId() );
        tag.name( responseTag.getName() );

        return tag.build();
    }

    protected Set<Tag> responseTagSetToTagSet(Set<ResponseTag> set) {
        if ( set == null ) {
            return null;
        }

        Set<Tag> set1 = new LinkedHashSet<Tag>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ResponseTag responseTag : set ) {
            set1.add( responseTagToTag( responseTag ) );
        }

        return set1;
    }
}
