package com.sergain.electricitymeter.service;

import com.sergain.electricitymeter.exception.EntityNotFoundException;
import com.sergain.electricitymeter.model.Account;
import com.sergain.electricitymeter.model.Invoice;
import com.sergain.electricitymeter.model.InvoiceWrapper;
import com.sergain.electricitymeter.model.User;
import com.sergain.electricitymeter.repository.AccountRepository;
import com.sergain.electricitymeter.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final AccountRepository accountRepository;

    public InvoiceService(
            InvoiceRepository invoiceRepository,
            AccountRepository accountRepository
            ) {
        this.invoiceRepository = invoiceRepository;
        this.accountRepository = accountRepository;
    }

    public void saveInvoice(Invoice invoice, User user){
        Invoice lastInvoice = invoiceRepository.findTopByOrderByIdDesc();
        Integer lastTransactionNumber = 0;
        if(lastInvoice == null){
            lastTransactionNumber = 200001;
        } else {
            lastTransactionNumber = Integer.valueOf(lastInvoice.getTransactionId()) + 1;
        }
        invoice.setTransactionId(lastTransactionNumber.toString());
        invoice.setPayee(user);
        invoiceRepository.save(invoice);
        Account account = accountRepository.findByAccountNumber(invoice.getInvoiceAccounts().getAccountNumber());
        Double amount = account.getOutstandAmount();
        amount = amount - invoice.getAmount();
        account.setOutstandAmount(amount);
        accountRepository.save(account);
    }

    public void updateInvoice(Invoice invoice){
        invoiceRepository.save(invoice);
    }

    public Invoice getInvoice(Invoice invoice) throws EntityNotFoundException {
        return Optional.ofNullable(invoiceRepository.findByTransactionId(invoice.getTransactionId())).orElseThrow(() ->
                new EntityNotFoundException(String.format("Transaction %s not found", invoice.getTransactionId())));
    }

    public List<Invoice> getInvoiceByParams(String accountNumber, String username) throws EntityNotFoundException {
        List<Invoice> invoiceList = new ArrayList<>();
        if(accountNumber.equals("") && !username.equals("")){
            invoiceList = invoiceRepository.findAllByPayee_Username(username);
        } else if(username.equals("") && !accountNumber.equals("")){
            invoiceList = invoiceRepository.findAllByInvoiceAccounts_AccountNumber(accountNumber);
        } else if(username.equals("") && accountNumber.equals("")){
            invoiceList = invoiceRepository.findAll();
        } else if(!username.equals("") && !accountNumber.equals("")){
            invoiceList = invoiceRepository.findAllByInvoiceAccounts_AccountNumberAndPayee_Username(accountNumber, username);
        }
        return Optional.ofNullable(invoiceList).orElseThrow(() ->
                new EntityNotFoundException("Invoices are empty "));
    }

    public List<InvoiceWrapper> getInvoiceByParamsWrapper(String accountNumber, String username) throws EntityNotFoundException {
        List<InvoiceWrapper> invoiceWrappers = new ArrayList<>();
        List<Invoice> invoices = new ArrayList<>();
        if(accountNumber.equals("") && !username.equals("")){
            invoices = invoiceRepository.findAllByPayee_Username(username);
        } else if(username.equals("") && !accountNumber.equals("")){
            invoices = invoiceRepository.findAllByInvoiceAccounts_AccountNumber(accountNumber);
        } else if(username.equals("") && accountNumber.equals("")){
            invoices = invoiceRepository.findAll();
        } else if(!username.equals("") && !accountNumber.equals("")){
            invoices = invoiceRepository.findAllByInvoiceAccounts_AccountNumberAndPayee_Username(accountNumber, username);
        }
        for(Invoice invoice: invoices){
            InvoiceWrapper invoiceWrapper = new InvoiceWrapper();
            invoiceWrapper.setId(invoice.getId());
            invoiceWrapper.setTransactionId(invoice.getTransactionId());
            invoiceWrapper.setInvoiceAccounts(invoice.getInvoiceAccounts().getAccountNumber());
            invoiceWrapper.setFirstName(invoice.getInvoiceAccounts().getConsumerFirstName());
            invoiceWrapper.setLastName(invoice.getInvoiceAccounts().getConsumerLastName());
            invoiceWrapper.setAmount(invoice.getAmount());
            invoiceWrapper.setMethod(invoice.getMethod());
            invoiceWrapper.setPayeeFirstName(invoice.getPayee().getFirstName());
            invoiceWrapper.setPayeeLastName(invoice.getPayee().getLastName());
            invoiceWrapper.setLastModifiedDate(invoice.getLastModifiedDate());
            invoiceWrappers.add(invoiceWrapper);
        }
        return invoiceWrappers;
    }
}
