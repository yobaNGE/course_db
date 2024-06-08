package org.example.course.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sale_component")
public class SaleComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "firm_id", nullable = false)
    private Firm firm;

    @ManyToOne
    @JoinColumn(name = "client_rep_id", nullable = false)
    private ClientRep clientRep;

    @ManyToOne
    @JoinColumn(name = "pager_number", nullable = false)
    private TradeAgent tradeAgent;

    @ManyToOne
    @JoinColumn(name = "check_id", nullable = false)
    private CheckSaleComponent checkSaleComponent;

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

    public ClientRep getClientRep() {
        return clientRep;
    }

    public void setClientRep(ClientRep clientRep) {
        this.clientRep = clientRep;
    }

    public TradeAgent getTradeAgent() {
        return tradeAgent;
    }

    public void setTradeAgent(TradeAgent tradeAgent) {
        this.tradeAgent = tradeAgent;
    }

    public CheckSaleComponent getCheckSaleComponent() {
        return checkSaleComponent;
    }

    public void setCheckSaleComponent(CheckSaleComponent checkSaleComponent) {
        this.checkSaleComponent = checkSaleComponent;
    }

    public Date getDateOfSupply() {
        return dateOfSupply;
    }

    public void setDateOfSupply(Date dateOfSupply) {
        this.dateOfSupply = dateOfSupply;
    }
}