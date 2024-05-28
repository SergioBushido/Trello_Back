package com.gestor.gestortareasbackend.model.tag.dto;

import com.gestor.gestortareasbackend.model.tag.Tag;
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
public class TagResponseMapperImpl implements TagResponseMapper {

    @Override
    public ResponseTag tagToResponseTag(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        ResponseTag.ResponseTagBuilder responseTag = ResponseTag.builder();

        responseTag.id( tag.getId() );
        responseTag.name( tag.getName() );

        return responseTag.build();
    }

    @Override
    public List<ResponseTag> tagsToResponseTags(List<Tag> tags) {
        if ( tags == null ) {
            return null;
        }

        List<ResponseTag> list = new ArrayList<ResponseTag>( tags.size() );
        for ( Tag tag : tags ) {
            list.add( tagToResponseTag( tag ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(RequestTag dto, Tag entity) {
        if ( dto == null ) {
            return;
        }

        entity.setName( dto.getName() );
    }
}
