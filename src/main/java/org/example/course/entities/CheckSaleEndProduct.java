package org.example.course.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "check_sale_end_product")
public class CheckSaleEndProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long articul;

    @Column(nullable = false)
    private Long scopeOfSupply;

    @ManyToOne
    @JoinColumn(name = "articul", insertable = false, updatable = false)
    private EndProduct endProduct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticul() {
        return articul;
    }

    public void setArticul(Long articul) {
        this.articul = articul;
    }

    public Long getScopeOfSupply() {
        return scopeOfSupply;
    }

    public void setScopeOfSupply(Long scopeOfSupply) {
        this.scopeOfSupply = scopeOfSupply;
    }

    public EndProduct getEndProduct() {
        return endProduct;
    }

    public void setEndProduct(EndProduct endProduct) {
        this.endProduct = endProduct;
    }

    // Getters and Setters
}