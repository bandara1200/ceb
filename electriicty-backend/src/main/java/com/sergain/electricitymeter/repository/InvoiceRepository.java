package com.sergain.electricitymeter.repository;

import com.sergain.electricitymeter.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByTransactionId(String invoiceId);
    List<Invoice> findAllByPayee_Username(String username);
    Invoice findTopByOrderByIdDesc();
    List<Invoice> findAllByInvoiceAccounts_AccountNumber(String accountNumber);
    List<Invoice> findAllByInvoiceAccounts_AccountNumberAndPayee_Username(String accountNumber, String username);
}
