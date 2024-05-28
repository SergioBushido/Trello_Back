package com.gestor.gestortareasbackend.repository;

import com.gestor.gestortareasbackend.model.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByNameContainingIgnoreCase(String name);
}