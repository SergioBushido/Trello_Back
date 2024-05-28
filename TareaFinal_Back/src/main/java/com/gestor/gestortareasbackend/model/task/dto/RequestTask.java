package com.gestor.gestortareasbackend.model.task.dto;

import com.gestor.gestortareasbackend.model.project.dto.ResponseProject;
import com.gestor.gestortareasbackend.model.tag.dto.ResponseTag;
import com.gestor.gestortareasbackend.model.user.dto.ResponseUser;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestTask {
    @NotBlank()
    private String name;
    private String status;
    private Long projectId;
    private Set<Long> tagIds;
    private String description;
}
