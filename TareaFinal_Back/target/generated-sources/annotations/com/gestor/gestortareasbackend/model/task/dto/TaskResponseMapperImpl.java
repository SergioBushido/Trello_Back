package com.gestor.gestortareasbackend.model.task.dto;

import com.gestor.gestortareasbackend.model.project.Project;
import com.gestor.gestortareasbackend.model.project.dto.ResponseProjectTask;
import com.gestor.gestortareasbackend.model.task.Task;
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
public class TaskResponseMapperImpl implements TaskResponseMapper {

    @Override
    public ResponseTask taskToResponseTask(Task task) {
        if ( task == null ) {
            return null;
        }

        ResponseTask.ResponseTaskBuilder responseTask = ResponseTask.builder();

        responseTask.id( task.getId() );
        responseTask.name( task.getName() );
        responseTask.status( task.getStatus() );
        responseTask.description( task.getDescription() );
        responseTask.tags( mapTags( task.getTags() ) );
        responseTask.project( projectToResponseProjectTask( task.getProject() ) );

        return responseTask.build();
    }

    @Override
    public List<ResponseTask> tasksToResponseTasks(List<Task> tasks) {
        if ( tasks == null ) {
            return null;
        }

        List<ResponseTask> list = new ArrayList<ResponseTask>( tasks.size() );
        for ( Task task : tasks ) {
            list.add( taskToResponseTask( task ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(RequestTask dto, Task entity) {
        if ( dto == null ) {
            return;
        }

        entity.setName( dto.getName() );
        entity.setStatus( dto.getStatus() );
        entity.setDescription( dto.getDescription() );
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
}
