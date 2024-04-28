package com.sergain.electricitymeter.service;

import com.sergain.electricitymeter.exception.EntityNotFoundException;
import com.sergain.electricitymeter.model.Account;
import com.sergain.electricitymeter.model.ConsumerAccount;
import com.sergain.electricitymeter.model.User;
import com.sergain.electricitymeter.repository.AccountRepository;
import com.sergain.electricitymeter.repository.ConsumerAccountRepository;
import com.sergain.electricitymeter.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final ConsumerAccountRepository consumerAccountRepository;

    public AccountService(AccountRepository accountRepository,
                          UserRepository userRepository,
                          ConsumerAccountRepository consumerAccountRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.consumerAccountRepository = consumerAccountRepository;
    }

    @Transactional
    public void saveAccount(Account account){
        User user = userRepository.findByUsername(account.getUsername());
        accountRepository.save(account);
        ConsumerAccount consumerAccount = new ConsumerAccount();
        consumerAccount.setAccount(account);
        consumerAccount.setConsumer(user);
        consumerAccountRepository.save(consumerAccount);
    }

    @Transactional
    public void updateAccount(Account account){
        User user = userRepository.findByUsername(account.getUsername());
        accountRepository.save(account);
        ConsumerAccount consumerAccount = new ConsumerAccount();
        consumerAccount.setAccount(account);
        consumerAccount.setConsumer(user);
        consumerAccountRepository.save(consumerAccount);
    }

    public Account getAccountByParams(String accountNumber, String mobileNumber) throws EntityNotFoundException{
        Account account = new Account();
        if(accountNumber.equals("") && !mobileNumber.equals("")){
            account = accountRepository.findByConsumerMobile(mobileNumber);
        } else if(!accountNumber.equals("") && mobileNumber.equals("")){
            account = accountRepository.findByAccountNumber(accountNumber);
        } else if(!accountNumber.equals("") && !mobileNumber.equals("")){
            account = accountRepository.findByAccountNumberAndConsumerMobile(accountNumber, mobileNumber);
        }
        return Optional.ofNullable(account).orElseThrow(() ->
                 new EntityNotFoundException(String.format("Account %s not found", accountNumber)));
    }

    public List<Account> getAccountByUserName(String username) throws EntityNotFoundException{
        List<ConsumerAccount> consumerAccountList = consumerAccountRepository.findByConsumer_Username(username);
        List<Account> accountList = new ArrayList<>();
        for(ConsumerAccount consumerAccount: consumerAccountList){
            Account account = new Account();
            account.setId(consumerAccount.getAccount().getId());
            account.setAccountNumber(consumerAccount.getAccount().getAccountNumber());
            account.setConsumerFirstName(consumerAccount.getAccount().getConsumerFirstName());
            account.setConsumerLastName(consumerAccount.getAccount().getConsumerLastName());
            account.setConsumerNIC(consumerAccount.getAccount().getConsumerNIC());
            account.setConsumerAddress(consumerAccount.getAccount().getConsumerAddress());
            account.setConsumerDistrict(consumerAccount.getAccount().getConsumerDistrict());
            account.setConsumerEmail(consumerAccount.getAccount().getConsumerEmail());
            account.setConsumerMobile(consumerAccount.getAccount().getConsumerMobile());
            account.setOutstandAmount(consumerAccount.getAccount().getOutstandAmount());
            accountList.add(account);
        }
        return Optional.ofNullable(accountList).orElseThrow(() ->
                new EntityNotFoundException("Accounts not found for this user "));
    }

    public List<Account> getAccountAll() throws EntityNotFoundException{
        return Optional.ofNullable(accountRepository.findAll()).orElseThrow(() ->
                new EntityNotFoundException("Accounts not found for this user "));
    }

}
