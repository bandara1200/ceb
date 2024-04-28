package com.sergain.electricitymeter.repository;

import com.sergain.electricitymeter.model.Account;
import com.sergain.electricitymeter.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository  extends JpaRepository<Unit, Long> {
    List<Unit> findByUnitAccounts_AccountNumber(String accountNumber);
    @Query(value = "SELECT * FROM unit u INNER JOIN account a ON u.account_id = a.id WHERE a.account_number = :accountNumber order by u.id desc limit 1 ", nativeQuery = true)
    Unit findByUnitAccounts_AccountNumberOrderByIdDesc(@Param("accountNumber") String accountNumber);
}
