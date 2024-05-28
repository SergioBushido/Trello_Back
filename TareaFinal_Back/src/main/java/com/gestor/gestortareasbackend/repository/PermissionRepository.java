package com.gestor.gestortareasbackend.repository;

import com.gestor.gestortareasbackend.model.permission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findByNameContainingIgnoreCase(String name);

}