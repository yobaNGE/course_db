package org.example.course.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "assembly")
public class Assembly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long articul;

    @Column(nullable = false)
    private Long amountToProduce;

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

    public Long getAmountToProduce() {
        return amountToProduce;
    }

    public void setAmountToProduce(Long amountToProduce) {
        this.amountToProduce = amountToProduce;
    }

    public Components getComponent() {
        return component;
    }

    public void setComponent(Components component) {
        this.component = component;
    }
}
