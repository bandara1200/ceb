package com.sergain.electricitymeter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class InvoiceWrapper {
    private Long id;
    private String transactionId;
    private String invoiceAccounts;
    private String firstName;
    private String lastName;
    private Double amount;
    private String method;
    private String payeeFirstName;
    private String payeeLastName;
    private Date lastModifiedDate;
}
