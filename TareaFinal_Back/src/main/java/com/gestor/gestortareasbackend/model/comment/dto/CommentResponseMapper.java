package com.gestor.gestortareasbackend.model.comment.dto;

import com.gestor.gestortareasbackend.model.comment.Comment;
import com.gestor.gestortareasbackend.model.task.dto.TaskResponseMapper;
import com.gestor.gestortareasbackend.model.user.dto.ResponseUser;
import com.gestor.gestortareasbackend.model.task.dto.ResponseTask;
import com.gestor.gestortareasbackend.model.user.dto.UserResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserResponseMapper.class, TaskResponseMapper.class})
public interface CommentResponseMapper {

    CommentResponseMapper INSTANCE = Mappers.getMapper(CommentResponseMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "task", target = "task")
    ResponseComment commentToResponseComment(Comment comment);

    List<ResponseComment> commentsToResponseComments(List<Comment> comments);

    void updateEntityFromDto(RequestComment dto, @MappingTarget Comment entity);
}
