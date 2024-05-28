package com.gestor.gestortareasbackend.model.permission.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestPermission {
    @NotBlank
    private String name;
}
