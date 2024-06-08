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

    public Components getComponent() {
        return component;
    }

    public void setComponent(Components component) {
        this.component = component;
    }

    // Getters and Setters
}