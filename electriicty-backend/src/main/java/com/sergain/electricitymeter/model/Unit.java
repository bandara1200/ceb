package com.sergain.electricitymeter.model;

import com.sergain.electricitymeter.audit.Auditable;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "unit")
public class Unit extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "month")
    @NotBlank(message = "Month is mandatory")
    private String month;
    @Column(name = "unit")
    private Integer unit;
    @Column(name = "amount")
    private Double amount;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id")
    private Account unitAccounts;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User meterReader;
}
