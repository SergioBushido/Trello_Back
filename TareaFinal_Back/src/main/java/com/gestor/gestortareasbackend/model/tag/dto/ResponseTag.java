package com.gestor.gestortareasbackend.model.tag.dto;

import com.gestor.gestortareasbackend.model.task.dto.ResponseTask;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseTag  {
    private Long id;
    private String name;
    //private Set<ResponseTask> responseTask;
}