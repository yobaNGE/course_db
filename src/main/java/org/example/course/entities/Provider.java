package org.example.course.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "providerIdSequence")
    @SequenceGenerator(name = "providerIdSequence", sequenceName = "provider_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String adress;

    @Column(nullable = false)
    private Long amountOfDebt;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)
    private List<Provide> provides;

    public Provider(String adress, Long amountOfDebt, String name, List<Provide> provides) {
        this.adress = adress;
        this.amountOfDebt = amountOfDebt;
        this.name = name;
        this.provides = provides;
    }

    public Provider() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAdress() {
        return adress;
    }

    public Long getAmountOfDebt() {
        return amountOfDebt;
    }

    public String getName() {
        return name;
    }

    public List<Provide> getProvides() {
        return provides;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setAmountOfDebt(Long amountOfDebt) {
        this.amountOfDebt = amountOfDebt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvides(List<Provide> provides) {
        this.provides = provides;
    }

    @Override
    public String toString() {
    return "Provider{" +
                "id=" + id +
                ", adress='" + adress + '\'' +
                ", amountOfDebt=" + amountOfDebt +
                ", name='" + name + '\'' +
                '}';
    }
}