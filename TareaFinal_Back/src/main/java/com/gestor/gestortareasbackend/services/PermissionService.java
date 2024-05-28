package com.gestor.gestortareasbackend.services;

import com.gestor.gestortareasbackend.model.permission.Permission;
import com.gestor.gestortareasbackend.model.permission.dto.PermissionRequestMapper;
import com.gestor.gestortareasbackend.model.permission.dto.PermissionResponseMapper;
import com.gestor.gestortareasbackend.model.permission.dto.RequestPermission;
import com.gestor.gestortareasbackend.model.permission.dto.ResponsePermission;
import com.gestor.gestortareasbackend.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionRequestMapper permissionRequestMapper;
    private final PermissionResponseMapper permissionResponseMapper;


    public Optional<ResponsePermission> getPermissionById(Long id) {
        final Optional<Permission> permissionOptional = permissionRepository.findById(id);
        return permissionOptional.map(permissionResponseMapper::entityToResponse);
    }

    @Transactional(readOnly = true)
    public List<ResponsePermission> getAllPermissions() {
        return permissionResponseMapper.entitiesToResponses(permissionRepository.findAll());
    }

    public ResponsePermission createPermission(RequestPermission requestPermission) {
        Permission permission = permissionRequestMapper.dtoToEntity(requestPermission);
        permission = permissionRepository.save(permission);
        return permissionResponseMapper.entityToResponse(permission);
    }

    public Optional<ResponsePermission> updatePermission(Long id, RequestPermission permissionDetails) {
        final Optional<Permission> existingPermission = permissionRepository.findById(id);
        existingPermission.ifPresent(role -> {
            permissionResponseMapper.updateEntityFromDto(permissionDetails, role);
            permissionRepository.save(role);
        });
        return existingPermission.map(permissionResponseMapper::entityToResponse);
    }

    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    public boolean existsById(final Long id) {
        return permissionRepository.existsById(id);
    }

    public List<ResponsePermission> findByPermission(final String name) {
        final var permissions = permissionRepository.findByNameContainingIgnoreCase(name);
        return permissionResponseMapper.entitiesToResponses(permissions);
    }
}

