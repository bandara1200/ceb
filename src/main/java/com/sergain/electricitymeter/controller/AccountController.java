package com.sergain.electricitymeter.controller;

import com.sergain.electricitymeter.exception.EntityNotFoundException;
import com.sergain.electricitymeter.model.Account;
import com.sergain.electricitymeter.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public ResponseEntity<Account> getAccount(@RequestParam String mobile_number, @RequestParam String account_number) throws EntityNotFoundException {
        return ResponseEntity.ok(accountService.getAccountByParams(account_number, mobile_number));
    }

    @PostMapping()
    public ResponseEntity<Void> saveAccount(@Valid @RequestBody Account account) throws EntityNotFoundException {
        accountService.saveAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping()
    public ResponseEntity<Void> updateProduct(@Valid @RequestBody Account account) throws EntityNotFoundException {
        accountService.updateAccount(account);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
