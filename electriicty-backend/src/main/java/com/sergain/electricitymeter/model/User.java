package com.sergain.electricitymeter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sergain.electricitymeter.audit.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @Column(name = "mobile")
    private String mobile;
    @OneToMany(mappedBy = "meterReader", cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Unit> units = new HashSet<>();
    @OneToMany(mappedBy = "payee", cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Invoice> invoices = new HashSet<>();
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    Set<UserRole> userRoles = new HashSet<>();
    @OneToMany(mappedBy = "consumer")
    @JsonIgnore
    Set<ConsumerAccount> consumerAccounts = new HashSet<>();
    @Transient
    private String roleName;
}
