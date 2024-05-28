package com.gestor.gestortareasbackend.controllers;


import com.gestor.gestortareasbackend.model.dto.auth.AuthenticationRequest;
import com.gestor.gestortareasbackend.model.dto.auth.AuthenticationResponse;
import com.gestor.gestortareasbackend.model.dto.auth.AvailableRequest;
import com.gestor.gestortareasbackend.model.dto.auth.RegisterRequest;
import com.gestor.gestortareasbackend.model.user.dto.ResponseUser;
import com.gestor.gestortareasbackend.services.AuthenticationService;
import com.gestor.gestortareasbackend.services.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "AuthenticationController", description = "Controlador para operaciones relacionadas con authenticación de usuarios")

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserProfileService userProfileService;

    @Operation(summary = "Registrar un usuario nuevo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente",
                    content = @Content(schema = @Schema(implementation = RegisterRequest.class))),
                @ApiResponse(responseCode = "404", description = "Usuario no registrado")
    })
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Operation(summary = "Authenticate un usuario registrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario authenticado exitosamente",
                    content = @Content(schema = @Schema(implementation = AuthenticationRequest.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no authentificado",
                    content = @Content(schema = @Schema(implementation = AuthenticationResponse.class)))
    })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @Operation(summary = "Refrescar el token de un usuario autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente",
                    content = @Content(schema = @Schema(implementation = HttpServletRequest.class))),
            @ApiResponse(responseCode = "404", description = "Token no refrescado")
    })
    @PostMapping("/refresh-token")
    public  ResponseEntity<AuthenticationResponse> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
       return ResponseEntity.ok(authenticationService.refreshToken(request, response));
    }

    @Operation(summary = "Comprobar si un email esta disponible para ser registrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La disponibilidad del email fue comprobada exitosamente",
                    content = @Content(schema = @Schema(implementation = AvailableRequest.class))),
            @ApiResponse(responseCode = "404", description = "La disponibilidad del email no fue comprobada")
    })
    @PostMapping("/is-available")
    public ResponseEntity<Boolean> isAvailable(@RequestBody AvailableRequest availableRequest) {
        boolean isAvailable = authenticationService.isAvailable(availableRequest);
        return ResponseEntity.ok(isAvailable);
    }

    @Operation(summary = "Obtener el perfil del usuario autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil del usuario autenticado obtenido exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseUser.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo obtener el perfil del usuario autenticado")
    })
    @GetMapping("/profile")
    public ResponseEntity<ResponseUser> getMyProfile() {
        ResponseUser myProfile = userProfileService.getMyProfile();
        return ResponseEntity.ok(myProfile);
    }

}