package com.gestor.gestortareasbackend.model.comment.dto;

import com.gestor.gestortareasbackend.model.comment.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentRequestMapper {

    @Mapping(target = "content", source = "content")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "task", source = "task")
    Comment dtoToEntity(RequestComment requestComment);
}
