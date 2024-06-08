package org.example.course.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "components")
public class Components {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articul;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Long costPerThing;

    @Column(nullable = false)
    private Long minAmount;

    @Column(nullable = false)
    private String firmCreator;

    @OneToMany(mappedBy = "component")
    private List<Assembly> assemblies;

    public Long getArticul() {
        return articul;
    }

    public void setArticul(Long articul) {
        this.articul = articul;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getCostPerThing() {
        return costPerThing;
    }

    public void setCostPerThing(Long costPerThing) {
        this.costPerThing = costPerThing;
    }

    public Long getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Long minAmount) {
        this.minAmount = minAmount;
    }

    public String getFirmCreator() {
        return firmCreator;
    }

    public void setFirmCreator(String firmCreator) {
        this.firmCreator = firmCreator;
    }

    public List<Assembly> getAssemblies() {
        return assemblies;
    }

    public void setAssemblies(List<Assembly> assemblies) {
        this.assemblies = assemblies;
    }
}