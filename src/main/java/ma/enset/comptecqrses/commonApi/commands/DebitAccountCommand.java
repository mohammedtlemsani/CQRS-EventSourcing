package ma.enset.comptecqrses.commonApi.commands;

import lombok.Getter;

public class DebitAccountCommand extends BaseCommand<String> {
    @Getter public Double amount;
    @Getter private String currency;

    public DebitAccountCommand(String id, Double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
