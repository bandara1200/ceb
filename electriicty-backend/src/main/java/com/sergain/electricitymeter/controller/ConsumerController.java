package com.sergain.electricitymeter.controller;

import com.sergain.electricitymeter.exception.EntityNotFoundException;
import com.sergain.electricitymeter.model.Account;
import com.sergain.electricitymeter.model.ConsumerAccount;
import com.sergain.electricitymeter.model.Invoice;
import com.sergain.electricitymeter.model.User;
import com.sergain.electricitymeter.service.AccountService;
import com.sergain.electricitymeter.service.InvoiceService;
import com.sergain.electricitymeter.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/consumer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ConsumerController {

    private final AccountService accountService;
    private final InvoiceService invoiceService;
    private final JwtTokenUtil jwtTokenUtil;

    public ConsumerController(
            AccountService accountService,
            InvoiceService invoiceService,
            JwtTokenUtil jwtTokenUtil
            ) {
        this.accountService = accountService;
        this.invoiceService = invoiceService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAccountByMobile(@RequestHeader("Authorization") String authorization, @RequestParam String username) throws EntityNotFoundException {
        User user =  jwtTokenUtil.checkJwtToken(authorization);
        if(user != null){
            return ResponseEntity.ok(accountService.getAccountByUserName(username));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<Invoice>> getInvoices(@RequestHeader("Authorization") String authorization, @RequestParam String account_number, @RequestParam String username) throws EntityNotFoundException {
        User user =  jwtTokenUtil.checkJwtToken(authorization);
        if(user != null){
            return ResponseEntity.ok(invoiceService.getInvoiceByParams(account_number, username));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("/payments")
    public ResponseEntity<Void> saveInvoice(@Valid @RequestHeader("Authorization") String authorization, @RequestBody Invoice invoice) throws EntityNotFoundException {
        User user =  jwtTokenUtil.checkJwtToken(authorization);
        if(user != null){
            invoiceService.saveInvoice(invoice, user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


}
