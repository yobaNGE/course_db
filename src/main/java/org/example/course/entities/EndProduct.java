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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimeToProduce() {
        return timeToProduce;
    }

    public void setTimeToProduce(Long timeToProduce) {
        this.timeToProduce = timeToProduce;
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

    public Assembly getAssembly() {
        return assembly;
    }

    public void setAssembly(Assembly assembly) {
        this.assembly = assembly;
    }

    public List<CheckSaleEndProduct> getCheckSaleEndProducts() {
        return checkSaleEndProducts;
    }

    public void setCheckSaleEndProducts(List<CheckSaleEndProduct> checkSaleEndProducts) {
        this.checkSaleEndProducts = checkSaleEndProducts;
    }
}
