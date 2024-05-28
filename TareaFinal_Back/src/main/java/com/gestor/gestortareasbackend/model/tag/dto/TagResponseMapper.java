package com.gestor.gestortareasbackend.model.tag.dto;

import com.gestor.gestortareasbackend.model.tag.Tag;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagResponseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ResponseTag tagToResponseTag(Tag tag);

    List<ResponseTag> tagsToResponseTags(List<Tag> tags);

    void updateEntityFromDto(RequestTag dto, @MappingTarget Tag entity);
}
