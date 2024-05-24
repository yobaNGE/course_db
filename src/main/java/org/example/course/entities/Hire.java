package org.example.course.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "hire")
public class Hire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pagerNumber;

    @ManyToOne
    @JoinColumn(name = "firm_id", nullable = false)
    private Firm firm;

    @Column(nullable = false)
    private Date dateOfContract;

    @Column(nullable = false)
    private Long salary;

    // Getters and Setters
}