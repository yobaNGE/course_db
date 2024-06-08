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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SaleEndProduct> getSaleEndProducts() {
        return saleEndProducts;
    }

    public void setSaleEndProducts(List<SaleEndProduct> saleEndProducts) {
        this.saleEndProducts = saleEndProducts;
    }
}