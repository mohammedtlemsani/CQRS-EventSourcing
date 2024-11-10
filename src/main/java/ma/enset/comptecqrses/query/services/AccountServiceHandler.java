package ma.enset.comptecqrses.query.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.comptecqrses.commonApi.enums.AccountStatus;
import ma.enset.comptecqrses.commonApi.events.AccountCreatedEvent;
import ma.enset.comptecqrses.query.entities.Account;
import ma.enset.comptecqrses.query.repositories.AccountRepository;
import ma.enset.comptecqrses.query.repositories.OperationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;
    @EventHandler
    public void on(AccountCreatedEvent event) {
        log.info("account created event received: {}",event.getId());
        accountRepository.save(new Account(
                event.getId(),
                event.getInitialBalance(),
                event.getCurrency(), AccountStatus.CREATED,null
        ));
       log.info("Account created: {}",event.getId());
    }

}
