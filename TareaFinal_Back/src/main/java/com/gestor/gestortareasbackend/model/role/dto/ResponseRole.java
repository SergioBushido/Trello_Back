package com.gestor.gestortareasbackend.model.role.dto;

import com.gestor.gestortareasbackend.model.permission.dto.ResponsePermission;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseRole {
    private Long id;
    private String name;
    private Set<ResponsePermission> permissions;
}
