package ma.enset.comptecqrses.query.services;

import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.comptecqrses.commonApi.enums.AccountStatus;
import ma.enset.comptecqrses.commonApi.enums.OperationType;
import ma.enset.comptecqrses.commonApi.events.AccountActivatedEvent;
import ma.enset.comptecqrses.commonApi.events.AccountCreatedEvent;
import ma.enset.comptecqrses.commonApi.events.AccountCreditedEvent;
import ma.enset.comptecqrses.commonApi.events.AccountDebitedEvent;
import ma.enset.comptecqrses.commonApi.queries.GetAllAccountQuery;
import ma.enset.comptecqrses.query.entities.Account;
import ma.enset.comptecqrses.query.entities.Operation;
import ma.enset.comptecqrses.query.repositories.AccountRepository;
import ma.enset.comptecqrses.query.repositories.OperationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AccountServiceHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    @EventHandler
    public void on(AccountCreatedEvent event) {
        log.info("account created event received: {}", event.getId());
        accountRepository.save(new Account(
                event.getId(),
                event.getInitialBalance(),
                event.getCurrency(), AccountStatus.CREATED, null
        ));
        log.info("Account created: {}", event.getId());
    }

    @EventHandler
    public void on(AccountActivatedEvent event) {
        log.info("account activated event received: {}", event.getId());
        Account account = accountRepository.findById(event.getId()).get();
        account.setStatus(AccountStatus.ACTIVATED);
        accountRepository.save(account);
        log.info("Account activated: {}", event.getId());
    }

    @EventHandler
    public void on(AccountCreditedEvent event) {
        log.info("account credited event received: {}", event.getId());
        Operation operation = new Operation();
        operation.setDate(new Date());
        operation.setAmount(event.getAmount());
        operation.setType(OperationType.CREDIT);
        Account account = accountRepository.findById(event.getId()).get();
        log.info("account balance: {}", account.getBalance());
        account.setBalance(account.getBalance() + event.getAmount());
        log.info("account balance: {}", account.getBalance());
        accountRepository.save(account);
        operation.setAccount(account);
        operationRepository.save(operation);
        log.info("Account credited: {}", event.getId());
    }

    @EventHandler
    public void on(AccountDebitedEvent event) {
        log.info("account debited event received: {}", event.getId());
        Operation operation = new Operation();
        operation.setDate(new Date());
        operation.setAmount(event.getAmount());
        operation.setType(OperationType.DEBIT);
        Account account = accountRepository.findById(event.getId()).get();
        account.setBalance(account.getBalance() - event.getAmount());
        accountRepository.save(account);
        operation.setAccount(account);
        operationRepository.save(operation);
        log.info("Account debited: {}", event.getId());
    }
    @QueryHandler
    public List<Account> accountList(GetAllAccountQuery query){ {
        return accountRepository.findAll();
    }
}}