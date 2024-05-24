package org.example.course.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "check_provide")
public class CheckProvide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long articul;

    @Column(nullable = false)
    private Long scopeOfSupply;

    @ManyToOne
    @JoinColumn(name = "articul", insertable = false, updatable = false)
    private Components component;

    // Getters and Setters
}