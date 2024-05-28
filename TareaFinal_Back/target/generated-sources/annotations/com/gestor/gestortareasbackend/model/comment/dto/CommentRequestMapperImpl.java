package com.gestor.gestortareasbackend.model.comment.dto;

import com.gestor.gestortareasbackend.model.comment.Comment;
import com.gestor.gestortareasbackend.model.project.Project;
import com.gestor.gestortareasbackend.model.project.dto.ResponseProjectTask;
import com.gestor.gestortareasbackend.model.tag.Tag;
import com.gestor.gestortareasbackend.model.tag.dto.ResponseTag;
import com.gestor.gestortareasbackend.model.task.Task;
import com.gestor.gestortareasbackend.model.task.dto.ResponseTask;
import com.gestor.gestortareasbackend.model.user.User;
import com.gestor.gestortareasbackend.model.user.dto.ResponseUser;
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
public class CommentRequestMapperImpl implements CommentRequestMapper {

    @Override
    public Comment dtoToEntity(RequestComment requestComment) {
        if ( requestComment == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.content( requestComment.getContent() );
        comment.user( responseUserToUser( requestComment.getUser() ) );
        comment.task( responseTaskToTask( requestComment.getTask() ) );

        return comment.build();
    }

    protected User responseUserToUser(ResponseUser responseUser) {
        if ( responseUser == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( responseUser.getId() );
        user.username( responseUser.getUsername() );
        user.name( responseUser.getName() );
        user.lastname( responseUser.getLastname() );
        user.email( responseUser.getEmail() );

        return user.build();
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

    protected Task responseTaskToTask(ResponseTask responseTask) {
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
}
