package com.gestor.gestortareasbackend.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseUser {
    private Long id;
    private String name;
    private String lastname;
    private String username;
    private String email;
}
