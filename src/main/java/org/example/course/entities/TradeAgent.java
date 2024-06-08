package org.example.course.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "trade_agent")
public class TradeAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pagerNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String atc;

    @OneToMany(mappedBy = "pagerNumber")
    private List<Hire> hires;

    public Long getPagerNumber() {
        return pagerNumber;
    }

    public void setPagerNumber(Long pagerNumber) {
        this.pagerNumber = pagerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAtc() {
        return atc;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }

    public List<Hire> getHires() {
        return hires;
    }

    public void setHires(List<Hire> hires) {
        this.hires = hires;
    }
}