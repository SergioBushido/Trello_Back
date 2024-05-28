package com.gestor.gestortareasbackend.model.project.dto;

import com.gestor.gestortareasbackend.model.user.dto.ResponseUser;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseProject {
    private Long id;
    private String name;
    private ResponseUser member;
    private Set<ResponseProjectTask> tasks;
}
