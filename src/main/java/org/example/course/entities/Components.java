package org.example.course.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "components")
public class Components {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articul;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Long costPerThing;

    @Column(nullable = false)
    private Long minAmount;

    @Column(nullable = false)
    private String firmCreator;

    @OneToMany(mappedBy = "component")
    private List<Assembly> assemblies;

    // Getters and Setters
}