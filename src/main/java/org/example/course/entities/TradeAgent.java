package org.example.course.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "trade_agent")
public class TradeAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pagerNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String atc;

    @OneToMany(mappedBy = "pagerNumber")
    private List<Hire> hires;

    // Getters and Setters
}