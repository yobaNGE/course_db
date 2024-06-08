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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public CheckProvide getCheckProvide() {
        return checkProvide;
    }

    public void setCheckProvide(CheckProvide checkProvide) {
        this.checkProvide = checkProvide;
    }

    public Date getDateOfSupply() {
        return dateOfSupply;
    }

    public void setDateOfSupply(Date dateOfSupply) {
        this.dateOfSupply = dateOfSupply;
    }

    // Getters and Setters
}

