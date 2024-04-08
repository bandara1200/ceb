package com.sergain.electricitymeter.repository;

import com.sergain.electricitymeter.model.ConsumerAccount;
import com.sergain.electricitymeter.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerAccountRepository extends JpaRepository<ConsumerAccount, Long> {
    List<ConsumerAccount> findByConsumer_Username(String username);
}
