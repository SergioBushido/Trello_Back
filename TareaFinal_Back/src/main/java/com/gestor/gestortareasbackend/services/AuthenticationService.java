package com.gestor.gestortareasbackend.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.gestor.gestortareasbackend.model.Token;
import com.gestor.gestortareasbackend.model.TokenType;
import com.gestor.gestortareasbackend.model.dto.auth.AvailableRequest;
import com.gestor.gestortareasbackend.model.user.User;
import com.gestor.gestortareasbackend.model.dto.auth.AuthenticationRequest;
import com.gestor.gestortareasbackend.model.dto.auth.AuthenticationResponse;
import com.gestor.gestortareasbackend.model.dto.auth.RegisterRequest;
import com.gestor.gestortareasbackend.model.role.Role;
import com.gestor.gestortareasbackend.repository.RoleRepository;
import com.gestor.gestortareasbackend.repository.TokenRepository;
import com.gestor.gestortareasbackend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // REGISTRATION
    public AuthenticationResponse register(RegisterRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElse(null);

        var user = User.builder()
                .name(request.getName())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken) // so user doesn't have to log in after registering by himself
                .refreshToken(refreshToken)
                .build();
    }

    // AUTHENTICATION
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NoSuchElementException("No user found with username: " + request.getUsername()));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    // SAVING TOKEN
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    // REVOKING TOKEN
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    // REFRESHING TOKEN
    public AuthenticationResponse refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return AuthenticationResponse.builder().build();  // Token no proporcionado o formato incorrecto
        }
        final String refreshToken = authHeader.substring(7);
        final String username = jwtService.extractUsername(refreshToken);
        if (username == null) {
            return AuthenticationResponse.builder().build();  // No se pudo extraer el username
        }

        var user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        var isTokenValid = tokenRepository.findByToken(refreshToken)
                .map(t -> !t.isExpired() && !t.isRevoked())
                .orElse(false);  // Verifica que el token no esté expirado ni revocado

        if (!isTokenValid) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // Configura el estado de la respuesta HTTP a no autorizado
            return AuthenticationResponse.builder().build();  // No genera nuevo token si el actual no es válido
        }

        if (jwtService.isTokenValid(refreshToken, user)) {
            var accessToken = jwtService.generateToken(user);
            revokeAllUserTokens(user);  // Revoca todos los tokens anteriores del usuario
            saveUserToken(user, accessToken);  // Guarda el nuevo token generado
            var authResponse = AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            new ObjectMapper().writeValue(response.getOutputStream(), authResponse);  // Escribe la respuesta en el flujo de salida
            return authResponse;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // Configura el estado de la respuesta HTTP a no autorizado si no se valida el token
        return AuthenticationResponse.builder().build();
    }


    public boolean isAvailable(AvailableRequest availableRequest) {
        return userRepository.findByEmail(availableRequest.getEmail()).isEmpty();
    }
}
