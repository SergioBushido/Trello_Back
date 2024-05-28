package com.gestor.gestortareasbackend.model.role.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestRole {
    @NotBlank
    private String name;
    private Set<Long> permissionIds;
}
