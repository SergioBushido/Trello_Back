package com.gestor.gestortareasbackend.model.project.dto;

import com.gestor.gestortareasbackend.model.user.dto.ResponseUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseProjectTask {
    private Long id;
    private String name;
    private ResponseUser member;
}