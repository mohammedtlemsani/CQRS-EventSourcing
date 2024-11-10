package ma.enset.comptecqrses.commands.aggregates;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.enset.comptecqrses.commonApi.commands.CreateAccountCommand;
import ma.enset.comptecqrses.commonApi.commands.CreditAccountCommand;
import ma.enset.comptecqrses.commonApi.commands.DebitAccountCommand;
import ma.enset.comptecqrses.commonApi.enums.AccountStatus;
import ma.enset.comptecqrses.commonApi.events.AccountActivatedEvent;
import ma.enset.comptecqrses.commonApi.events.AccountCreatedEvent;
import ma.enset.comptecqrses.commonApi.events.AccountCreditedEvent;
import ma.enset.comptecqrses.commonApi.events.AccountDebitedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private Double balance;
    private String currency;
    private AccountStatus status;
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getInitialBalance(),
                command.getCurrency()
        ));
        }
    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.accountId = event.getId();
        this.balance = event.getInitialBalance();
        this.currency = event.getCurrency();
        this.status = AccountStatus.CREATED;
        AggregateLifecycle.apply(new AccountActivatedEvent(event.getId(), AccountStatus.ACTIVATED));
    }
    @EventSourcingHandler
    public void on(AccountActivatedEvent event) {
        this.status = event.getStatus();
    }
    @CommandHandler
    public void handle(CreditAccountCommand command) {
        AggregateLifecycle.apply(new AccountCreditedEvent(command.getId(), command.getAmount(), command.getCurrency()));
    }
    @EventSourcingHandler
    public void on(AccountCreditedEvent event) {
        this.balance += event.getAmount();
    }
    @CommandHandler
    public void handle(DebitAccountCommand command) {
        AggregateLifecycle.apply(new AccountDebitedEvent(command.getId(), command.getAmount(), command.getCurrency()));
    }
    @EventSourcingHandler
    public void on(AccountDebitedEvent event) {
        this.balance -= event.getAmount();
    }

}
