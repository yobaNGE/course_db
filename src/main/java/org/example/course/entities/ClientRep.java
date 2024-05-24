package org.example.course.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "client_rep")
public class ClientRep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String organisation;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "clientRep")
    private List<SaleEndProduct> saleEndProducts;

    // Getters and Setters
}