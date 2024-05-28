package com.gestor.gestortareasbackend.model.permission.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponsePermission {
    private Long id;
    private String name;
}