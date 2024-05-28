package com.gestor.gestortareasbackend.model.user.dto;

import com.gestor.gestortareasbackend.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastname", source = "lastname")
    @Mapping(target = "email", source = "email")
    ResponseUser userToResponseUser(User user);

    List<ResponseUser> userToResponseUser(List<User> users);


}
