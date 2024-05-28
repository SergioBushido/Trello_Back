package com.gestor.gestortareasbackend.controllers;

import com.gestor.gestortareasbackend.model.permission.dto.RequestPermission;
import com.gestor.gestortareasbackend.model.permission.dto.ResponsePermission;
import com.gestor.gestortareasbackend.services.PermissionService;
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

@Tag(name = "PermissionController", description = "Controlador para operaciones relacionadas con los permisos de usuarios")
@RestController
@RequestMapping("api/v1/permissions")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @Operation(summary = "Obtener todos los permisos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permisos obtenidos exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponsePermission.class)))
    })
    @GetMapping
    public ResponseEntity<List<ResponsePermission>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @Operation(summary = "Obtener un permiso por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permiso obtenido exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponsePermission.class))),
            @ApiResponse(responseCode = "404", description = "Permiso no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponsePermission> getPermissionById(@PathVariable Long id) {
        return permissionService.getPermissionById(id)
                .map(permission -> ResponseEntity.ok().body(permission))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo permiso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permiso creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponsePermission.class)))
    })
    @PostMapping
    public ResponseEntity<ResponsePermission> createPermission(@RequestBody RequestPermission requestPermission) {
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.createPermission(requestPermission));
    }

    @Operation(summary = "Actualizar un permiso existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permiso actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponsePermission.class))),
            @ApiResponse(responseCode = "404", description = "Permiso no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponsePermission> updatePermission(@PathVariable Long id, @RequestBody RequestPermission permissionDetails) {
        return permissionService.updatePermission(id, permissionDetails)
                .map(permission -> ResponseEntity.ok().body(permission))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un permiso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Permiso eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Permiso no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        if (permissionService.existsById(id)) {
            permissionService.deletePermission(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
