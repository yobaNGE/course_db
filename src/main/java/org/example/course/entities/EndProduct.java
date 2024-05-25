package org.example.course.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "end_product")
public class EndProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long timeToProduce;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Long costPerThing;

    @Column(nullable = false)
    private Long minAmount;

    @ManyToOne
    @JoinColumn(name = "assembly_id", nullable = false)
    private Assembly assembly;

    @OneToMany(mappedBy = "endProduct")
    private List<CheckSaleEndProduct> checkSaleEndProducts;

    // Getters and Setters
}
