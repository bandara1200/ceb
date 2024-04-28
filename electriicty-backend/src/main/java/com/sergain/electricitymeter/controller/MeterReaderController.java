package com.sergain.electricitymeter.controller;

import com.sergain.electricitymeter.exception.EntityNotFoundException;
import com.sergain.electricitymeter.model.Unit;
import com.sergain.electricitymeter.model.User;
import com.sergain.electricitymeter.service.UnitService;
import com.sergain.electricitymeter.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/meter-readings")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MeterReaderController {

    private final UnitService unitService;
    private final JwtTokenUtil jwtTokenUtil;

    public MeterReaderController(
            UnitService unitService,
            JwtTokenUtil jwtTokenUtil) {
        this.unitService = unitService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Unit> getUnitsByAccount(@RequestHeader("Authorization") String authorization, @RequestParam String account_no) throws EntityNotFoundException {
        User user = jwtTokenUtil.checkJwtToken(authorization);
        if(user != null){
            return ResponseEntity.ok(unitService.getLastUnitByAccount(account_no));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Void> saveUnit(@RequestHeader("Authorization") String authorization, @RequestBody Unit unit) throws EntityNotFoundException {
        User user =  jwtTokenUtil.checkJwtToken(authorization);
        if(user != null){
            unitService.saveUnit(unit, user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PutMapping()
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> updateUnit(@RequestHeader("Authorization") String authorization, @RequestBody Unit unit) throws EntityNotFoundException {
        User user =  jwtTokenUtil.checkJwtToken(authorization);
        if(user != null){
            unitService.updateUnit(unit);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
