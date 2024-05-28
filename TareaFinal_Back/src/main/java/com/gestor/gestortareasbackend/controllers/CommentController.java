package com.gestor.gestortareasbackend.controllers;

import com.gestor.gestortareasbackend.model.comment.dto.RequestComment;
import com.gestor.gestortareasbackend.model.comment.dto.ResponseComment;
import com.gestor.gestortareasbackend.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CommentController", description = "Controlador para operaciones relacionadas con los comentarios de las tareas")
@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Obtener todos los comentarios de una tarea específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentarios obtenidos exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseComment.class)))
    })
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<ResponseComment>> getAllCommentsByTaskId(@PathVariable Long taskId) {
        return ResponseEntity.ok(commentService.getAllCommentsByTaskId(taskId));
    }

    @Operation(summary = "Obtener un comentario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentario obtenido exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseComment.class))),
            @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseComment> getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo comentario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comentario creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseComment.class)))
    })
    @PostMapping
    public ResponseEntity<ResponseComment> createComment(@RequestBody RequestComment requestComment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(requestComment));
    }

    @Operation(summary = "Actualizar un comentario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentario actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseComment.class))),
            @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseComment> updateComment(@PathVariable Long id, @RequestBody RequestComment commentDetails) {
        return commentService.updateComment(id, commentDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un comentario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comentario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        if (commentService.existsById(id)) {
            commentService.deleteComment(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
