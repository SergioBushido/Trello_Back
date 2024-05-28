package com.gestor.gestortareasbackend.controllers;


import com.gestor.gestortareasbackend.model.role.dto.RequestRole;
import com.gestor.gestortareasbackend.model.role.dto.ResponseRole;
import com.gestor.gestortareasbackend.services.RoleService;
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

@Tag(name = "RoleController", description = "Controlador para operaciones relacionadas con los roles de usuarios")
@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @Operation(summary = "Obtener todos los roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles obtenidos exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseRole.class)))
    })
    @GetMapping
    public ResponseEntity<List<ResponseRole>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @Operation(summary = "Obtener un rol por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol obtenido exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseRole.class))),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseRole> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id)
                .map(rol -> ResponseEntity.ok().body(rol))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rol creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseRole.class)))
    })
    @PostMapping
    public ResponseEntity<ResponseRole> createRole(@RequestBody RequestRole requestRole) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRoleWithPermissions(requestRole));
    }

    @Operation(summary = "Actualizar un rol existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseRole.class))),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseRole> updateRole(@PathVariable Long id, @RequestBody RequestRole roleDetails) {
        return roleService.updateRole(id, roleDetails)
                .map(role -> ResponseEntity.ok().body(role))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Rol eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        if (roleService.existsById(id)) {
            roleService.deleteRole(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
