package org.example.course.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "assembly")
public class Assembly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long articul;

    @Column(nullable = false)
    private Long amountToProduce;

    @ManyToOne
    @JoinColumn(name = "articul", insertable = false, updatable = false)
    private Components component;

    // Getters and Setters
}
