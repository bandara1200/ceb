package com.sergain.electricitymeter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sergain.electricitymeter.audit.Auditable;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "consumer_account")
public class ConsumerAccount extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User consumer;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
