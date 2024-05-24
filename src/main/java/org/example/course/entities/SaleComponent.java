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

    // Getters and Setters
}