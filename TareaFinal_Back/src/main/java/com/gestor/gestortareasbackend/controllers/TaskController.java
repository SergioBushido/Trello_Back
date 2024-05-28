package com.gestor.gestortareasbackend.controllers;

import com.gestor.gestortareasbackend.model.task.dto.RequestTask;
import com.gestor.gestortareasbackend.model.task.dto.ResponseTask;
import com.gestor.gestortareasbackend.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TaskController", description = "Controlador para operaciones relacionadas con las tareas")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Obtener una tarea por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea obtenida exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseTask.class))),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTask> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las tareas")
    @ApiResponse(responseCode = "200", description = "Todas las tareas obtenidas exitosamente",
            content = @Content(schema = @Schema(implementation = ResponseTask.class)))
    @GetMapping
    public List<ResponseTask> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Operation(summary = "Crear una nueva tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea creada exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseTask.class)))
    })
    @PostMapping
    public ResponseEntity<ResponseTask> createTask(@RequestBody RequestTask requestTask) {
        ResponseTask createdTask = taskService.createTask(requestTask);
        return ResponseEntity.ok(createdTask);
    }

    @Operation(summary = "Actualizar una tarea existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea actualizada exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseTask.class))),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseTask> updateTask(@PathVariable Long id, @RequestBody RequestTask requestTask) {
        return taskService.updateTask(id, requestTask)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskService.existsById(id)) {
            taskService.deleteTask(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar tareas por nombre")
    @ApiResponse(responseCode = "200", description = "Búsqueda completada exitosamente",
            content = @Content(schema = @Schema(implementation = ResponseTask.class)))
    @GetMapping("/search")
    public List<ResponseTask> findTasksByName(@RequestParam String name) {
        return taskService.findTasksByName(name);
    }
}
