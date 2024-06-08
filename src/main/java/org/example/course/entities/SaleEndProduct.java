package org.example.course.entities;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sale_end_product")
public class SaleEndProduct {
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
    private CheckSaleEndProduct checkSaleEndProduct;

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

    public CheckSaleEndProduct getCheckSaleEndProduct() {
        return checkSaleEndProduct;
    }

    public void setCheckSaleEndProduct(CheckSaleEndProduct checkSaleEndProduct) {
        this.checkSaleEndProduct = checkSaleEndProduct;
    }

    public Date getDateOfSupply() {
        return dateOfSupply;
    }

    public void setDateOfSupply(Date dateOfSupply) {
        this.dateOfSupply = dateOfSupply;
    }
}