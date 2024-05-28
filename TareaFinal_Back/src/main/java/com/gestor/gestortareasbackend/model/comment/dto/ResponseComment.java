package com.gestor.gestortareasbackend.model.comment.dto;

import com.gestor.gestortareasbackend.model.task.dto.ResponseTask;
import com.gestor.gestortareasbackend.model.user.dto.ResponseUser;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseComment {
    private Long id;
    private String content;
    private ResponseUser user;
    private ResponseTask task;
}
