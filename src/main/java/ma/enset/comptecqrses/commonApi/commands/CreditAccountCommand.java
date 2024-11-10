package ma.enset.comptecqrses.commonApi.commands;

import lombok.Getter;

public class CreditAccountCommand extends BaseCommand<String> {
    @Getter public Double amount;
    @Getter private String currency;

    public CreditAccountCommand(String id, Double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
