package org.example.course.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "provide")
public class Provide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "firm_id", nullable = false)
    private Firm firm;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "check_id", nullable = false)
    private CheckProvide checkProvide;

    @Column(nullable = false)
    private Date dateOfSupply;

    // Getters and Setters
}

