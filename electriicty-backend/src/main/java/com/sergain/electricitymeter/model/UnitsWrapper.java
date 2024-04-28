package com.sergain.electricitymeter.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UnitsWrapper {
    private Long id;
    private String unitAccounts;
    private String firstName;
    private String lastName;
    private String month;
    private Integer unit;
    private Double amount;
    private Date lastModifiedDate;
}
