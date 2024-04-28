package com.sergain.electricitymeter.service;

import com.sergain.electricitymeter.exception.EntityNotFoundException;
import com.sergain.electricitymeter.model.Account;
import com.sergain.electricitymeter.model.Unit;
import com.sergain.electricitymeter.model.UnitsWrapper;
import com.sergain.electricitymeter.model.User;
import com.sergain.electricitymeter.repository.AccountRepository;
import com.sergain.electricitymeter.repository.UnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UnitService {

    private final UnitRepository unitRepository;
    private final AccountRepository accountRepository;

    public UnitService(
            UnitRepository unitRepository,
            AccountRepository accountRepository
    ) {
        this.unitRepository = unitRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void saveUnit(Unit unit, User user){
        unit.setMeterReader(user);
        unitRepository.save(unit);
        Account account = accountRepository.findByAccountNumber(unit.getUnitAccounts().getAccountNumber());
        Double amount = account.getOutstandAmount();
        if(amount != null){
            amount = amount + unit.getAmount();
        } else {
            amount = unit.getAmount();
        }
        account.setOutstandAmount(amount);
        accountRepository.save(account);
    }

    public void updateUnit(Unit unit){
        unitRepository.save(unit);
    }

    public Unit getUnit(Unit unit) throws EntityNotFoundException {
        return unitRepository.findById(unit.getId()).orElseThrow(() ->
                new EntityNotFoundException(String.format("Unit %s not found", unit.getUnit())));
    }

    public Unit getLastUnitByAccount(String accountNumber) throws EntityNotFoundException {
        return  Optional.ofNullable(unitRepository.findByUnitAccounts_AccountNumberOrderByIdDesc(accountNumber)).orElseThrow(() ->
                new EntityNotFoundException(String.format("Units are empty for this account number %s ", accountNumber)));
    }

    public List<UnitsWrapper> getUnits(String accountNumber) throws EntityNotFoundException {
        List<UnitsWrapper> unitsWrappers = new ArrayList<>();
        List<Unit> units = new ArrayList<>();
        if(accountNumber.equals("")){
            units = unitRepository.findAll();
        } else {
            units = unitRepository.findByUnitAccounts_AccountNumber(accountNumber);
        }
        for(Unit unit : units){
            UnitsWrapper unitsWrapper = new UnitsWrapper();
            unitsWrapper.setId(unit.getId());
            unitsWrapper.setUnitAccounts(unit.getUnitAccounts().getAccountNumber());
            unitsWrapper.setFirstName(unit.getUnitAccounts().getConsumerFirstName());
            unitsWrapper.setLastName(unit.getUnitAccounts().getConsumerLastName());
            unitsWrapper.setMonth(unit.getMonth());
            unitsWrapper.setUnit(unit.getUnit());
            unitsWrapper.setAmount(unit.getAmount());
            unitsWrapper.setLastModifiedDate(unit.getLastModifiedDate());
            unitsWrappers.add(unitsWrapper);
        }
        return unitsWrappers;
    }
}
