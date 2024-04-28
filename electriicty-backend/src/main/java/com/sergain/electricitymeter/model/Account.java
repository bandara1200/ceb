package com.sergain.electricitymeter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sergain.electricitymeter.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number", unique=true)
    private String accountNumber;
    @Column(name = "consumer_first_name")
    @NotBlank(message = "First Name is mandatory")
    private String consumerFirstName;
    @Column(name = "consumer_last_name")
    @NotBlank(message = "Last Name is mandatory")
    private String consumerLastName;
    @Column(name = "consumer_nic")
    @NotBlank(message = "NIC is mandatory")
    private String consumerNIC;
    @Column(name = "consumer_address")
    private String consumerAddress;
    @Column(name = "consumer_district")
    @NotBlank(message = "District is mandatory")
    private String consumerDistrict;
    @Column(name = "consumer_email")
    @NotBlank(message = "Email is mandatory")
    private String consumerEmail;
    @Column(name = "consumer_mobile", unique=true)
    @NotBlank(message = "Mobile is mandatory")
    private String consumerMobile;
    @Column(name = "outstanding_amount")
    private Double outstandAmount;
    @OneToMany(mappedBy = "unitAccounts", cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Unit> units = new HashSet<>();
    @OneToMany(mappedBy = "invoiceAccounts", cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Invoice> invoices = new HashSet<>();
    @OneToMany(mappedBy = "account")
    @JsonIgnore
    Set<ConsumerAccount> consumerAccounts = new HashSet<>();
    @Transient
    private String username;
}
