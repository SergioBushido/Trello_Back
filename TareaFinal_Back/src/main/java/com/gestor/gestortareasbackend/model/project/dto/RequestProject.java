package com.gestor.gestortareasbackend.model.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestProject {
    @NotBlank
    private String name;
    private Long memberId;

}
