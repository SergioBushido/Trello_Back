package com.gestor.gestortareasbackend.model.permission;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(length = 150, unique = true, nullable = false)
    private String name;

}


