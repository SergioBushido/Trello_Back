package com.gestor.gestortareasbackend.controllers;

import com.gestor.gestortareasbackend.model.project.dto.RequestProject;
import com.gestor.gestortareasbackend.model.project.dto.ResponseProject;
import com.gestor.gestortareasbackend.services.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ProjectController", description = "Controlador para operaciones relacionadas con los proyectos")
@RestController
@RequestMapping("api/v1/projects")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "Obtener todos los proyectos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyectos obtenidos exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseProject.class)))
    })
    @GetMapping
    public ResponseEntity<List<ResponseProject>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @Operation(summary = "Obtener un proyecto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyecto obtenido exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseProject.class))),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseProject> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(project -> ResponseEntity.ok().body(project))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo proyecto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proyecto creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseProject.class)))
    })
    @PostMapping
    public ResponseEntity<ResponseProject> createProject(@RequestBody RequestProject requestProject) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(requestProject));
    }

    @Operation(summary = "Actualizar un proyecto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyecto actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseProject.class))),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseProject> updateProject(@PathVariable Long id, @RequestBody RequestProject projectDetails) {
        return projectService.updateProject(id, projectDetails)
                .map(project -> ResponseEntity.ok().body(project))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un proyecto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Proyecto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (projectService.existsById(id)) {
            projectService.deleteProject(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
