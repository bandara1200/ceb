package com.sergain.electricitymeter.repository;

import com.sergain.electricitymeter.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
    Account findByConsumerMobile(String mobileNumber);
    Account findByAccountNumberAndConsumerMobile(String accountNumber, String mobileNumber);
    Account findTopByOrderByIdDesc();
}
