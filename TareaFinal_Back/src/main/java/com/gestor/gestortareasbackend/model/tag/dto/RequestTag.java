package com.gestor.gestortareasbackend.model.tag.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestTag {
    @NotBlank
    private String name;
}
