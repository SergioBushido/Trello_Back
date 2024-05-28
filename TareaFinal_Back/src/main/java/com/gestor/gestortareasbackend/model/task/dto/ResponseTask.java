package com.gestor.gestortareasbackend.model.task.dto;

import com.gestor.gestortareasbackend.model.project.dto.ResponseProject;
import com.gestor.gestortareasbackend.model.project.dto.ResponseProjectTask;
import com.gestor.gestortareasbackend.model.tag.dto.ResponseTag;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseTask {
    private Long id;
    private String name;
    private String status;
    private ResponseProjectTask project;
    private Set<ResponseTag> tags;
    private String description;
}
