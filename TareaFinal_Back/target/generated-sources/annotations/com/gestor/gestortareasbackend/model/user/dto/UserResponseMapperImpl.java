package com.gestor.gestortareasbackend.model.user.dto;

import com.gestor.gestortareasbackend.model.user.User;
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
public class UserResponseMapperImpl implements UserResponseMapper {

    @Override
    public ResponseUser userToResponseUser(User user) {
        if ( user == null ) {
            return null;
        }

        ResponseUser.ResponseUserBuilder responseUser = ResponseUser.builder();

        responseUser.id( user.getId() );
        responseUser.username( user.getUsername() );
        responseUser.name( user.getName() );
        responseUser.lastname( user.getLastname() );
        responseUser.email( user.getEmail() );

        return responseUser.build();
    }

    @Override
    public List<ResponseUser> userToResponseUser(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<ResponseUser> list = new ArrayList<ResponseUser>( users.size() );
        for ( User user : users ) {
            list.add( userToResponseUser( user ) );
        }

        return list;
    }
}
