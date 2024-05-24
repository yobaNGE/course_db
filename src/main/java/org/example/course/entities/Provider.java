package org.example.course.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String adress;

    @Column(nullable = false)
    private Long amountOfDebt;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "provider")
    private List<Provide> provides;

    // Getters and Setters
}