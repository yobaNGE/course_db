package org.example.course.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "check_sale_component")
public class CheckSaleComponent {
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