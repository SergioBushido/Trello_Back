package com.gestor.gestortareasbackend.model.comment.dto;

import com.gestor.gestortareasbackend.model.task.dto.ResponseTask;
import com.gestor.gestortareasbackend.model.user.dto.ResponseUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestComment {

    @NotBlank(message = "El contenido del comentario no puede estar vacío")
    private String content;

    @NotEmpty
    private ResponseUser user;

    @NotEmpty
    private ResponseTask task;
}
