package com.gestor.gestortareasbackend.model.tag;

import com.gestor.gestortareasbackend.model.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //una etiqueta puede tener muchas tareas
    @ManyToMany(mappedBy = "tags")
    private Set<Task> tasks = new HashSet<>();
}
