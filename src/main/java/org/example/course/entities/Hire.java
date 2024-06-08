package org.example.course.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "hire")
public class Hire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pagerNumber;

    @ManyToOne
    @JoinColumn(name = "firm_id", nullable = false)
    private Firm firm;

    @Column(nullable = false)
    private Date dateOfContract;

    @Column(nullable = false)
    private Long salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPagerNumber() {
        return pagerNumber;
    }

    public void setPagerNumber(Long pagerNumber) {
        this.pagerNumber = pagerNumber;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public Date getDateOfContract() {
        return dateOfContract;
    }

    public void setDateOfContract(Date dateOfContract) {
        this.dateOfContract = dateOfContract;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    // Getters and Setters
}