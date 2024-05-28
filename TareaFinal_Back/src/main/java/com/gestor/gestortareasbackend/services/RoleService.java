package com.gestor.gestortareasbackend.services;

import com.gestor.gestortareasbackend.model.permission.Permission;
import com.gestor.gestortareasbackend.model.role.Role;
import com.gestor.gestortareasbackend.model.role.dto.RequestRole;
import com.gestor.gestortareasbackend.model.role.dto.ResponseRole;
import com.gestor.gestortareasbackend.model.role.dto.RoleResponseMapper;
import com.gestor.gestortareasbackend.repository.PermissionRepository;
import com.gestor.gestortareasbackend.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleResponseMapper roleResponseMapper;

    public Optional<ResponseRole> getRoleById(Long id) {
        final Optional<Role> roleOptional = roleRepository.findById(id);
        return roleOptional.map(roleResponseMapper::roleToResponseRole);
    }

    @Transactional(readOnly = true)
    public List<ResponseRole> getAllRoles() {
        return roleResponseMapper.rolesToResponseRoles(roleRepository.findAll());
    }

    @Transactional
    public ResponseRole createRoleWithPermissions(RequestRole requestRole) {
        final var role = Role.builder().name(requestRole.getName()).build();

        if (requestRole.getPermissionIds() != null && !requestRole.getPermissionIds().isEmpty()) {
            role.setPermissions(findPermissionsByIds(requestRole.getPermissionIds()));
        } else {
            role.setPermissions(new HashSet<>());
        }

        return roleResponseMapper.roleToResponseRole(roleRepository.save(role));
    }

    private Set<Permission> findPermissionsByIds(Set<Long> permissionIds) {
        return new HashSet<>(permissionRepository.findAllById(permissionIds));
    }

    public Optional<ResponseRole> updateRole(Long id, RequestRole roleDetails) {
        final Optional<Role> existingRole = roleRepository.findById(id);
        existingRole.ifPresent(role -> {
            roleResponseMapper.updateEntityFromDto(roleDetails, role);
            roleRepository.save(role);
        });
        return existingRole.map(roleResponseMapper::roleToResponseRole);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public boolean existsById(final Long id) {
        return roleRepository.existsById(id);
    }


    public List<ResponseRole> findByName(final String name) {
        final var roles = roleRepository.findByNameContainingIgnoreCase(name);
        return roleResponseMapper.rolesToResponseRoles(roles);
    }
}
