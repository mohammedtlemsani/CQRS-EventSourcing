package ma.enset.comptecqrses.commonApi.events;

import lombok.Getter;

public class AccountCreatedEvent extends BaseEvent<String> {
    @Getter public Double initialBalance;
    @Getter private String currency;
    public AccountCreatedEvent(String id, Double initialBalance, String currency) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
    }
}
