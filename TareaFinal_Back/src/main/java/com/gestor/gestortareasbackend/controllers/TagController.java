package com.gestor.gestortareasbackend.controllers;

import com.gestor.gestortareasbackend.model.tag.dto.RequestTag;
import com.gestor.gestortareasbackend.model.tag.dto.ResponseTag;
import com.gestor.gestortareasbackend.services.TagService;
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

@Tag(name = "TagController", description = "Controlador para operaciones relacionadas con las etiquetas")
@RestController
@RequestMapping("api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @Operation(summary = "Obtener todas las etiquetas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Etiquetas obtenidas exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseTag.class)))
    })
    @GetMapping
    public ResponseEntity<List<ResponseTag>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @Operation(summary = "Obtener una etiqueta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Etiqueta obtenida exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseTag.class))),
            @ApiResponse(responseCode = "404", description = "Etiqueta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTag> getTagById(@PathVariable Long id) {
        return tagService.getTagById(id)
                .map(tag -> ResponseEntity.ok().body(tag))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva etiqueta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Etiqueta creada exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseTag.class)))
    })
    @PostMapping
    public ResponseEntity<ResponseTag> createTag(@RequestBody RequestTag requestTag) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.createTag(requestTag));
    }

    @Operation(summary = "Actualizar una etiqueta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Etiqueta actualizada exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseTag.class))),
            @ApiResponse(responseCode = "404", description = "Etiqueta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseTag> updateTag(@PathVariable Long id, @RequestBody RequestTag tagDetails) {
        return tagService.updateTag(id, tagDetails)
                .map(tag -> ResponseEntity.ok().body(tag))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una etiqueta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Etiqueta eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Etiqueta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        if (tagService.existsById(id)) {
            tagService.deleteTag(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
