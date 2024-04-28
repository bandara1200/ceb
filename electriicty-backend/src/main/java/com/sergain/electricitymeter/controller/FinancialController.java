package com.sergain.electricitymeter.controller;

import com.sergain.electricitymeter.exception.EntityNotFoundException;
import com.sergain.electricitymeter.model.InvoiceWrapper;
import com.sergain.electricitymeter.model.UnitsWrapper;
import com.sergain.electricitymeter.model.User;
import com.sergain.electricitymeter.service.InvoiceService;
import com.sergain.electricitymeter.service.UnitService;
import com.sergain.electricitymeter.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/controller")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FinancialController {

    private final InvoiceService invoiceService;
    private final UnitService unitService;
    private final JwtTokenUtil jwtTokenUtil;

    public FinancialController(
            InvoiceService invoiceService,
            UnitService unitService,
            JwtTokenUtil jwtTokenUtil) {
        this.invoiceService = invoiceService;
        this.unitService = unitService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<InvoiceWrapper>> getInvoice(@RequestHeader("Authorization") String authorization, @RequestParam String account_number, @RequestParam String username) throws EntityNotFoundException {
        User user = jwtTokenUtil.checkJwtToken(authorization);
        if(user != null){
            return ResponseEntity.ok(invoiceService.getInvoiceByParamsWrapper(account_number, username));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


    @GetMapping("/readings")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<UnitsWrapper>> getUnits(@RequestHeader("Authorization") String authorization, @RequestParam String account_no) throws EntityNotFoundException {
        User user =  jwtTokenUtil.checkJwtToken(authorization);
        if(user != null){
            return ResponseEntity.ok(unitService.getUnits(account_no));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
