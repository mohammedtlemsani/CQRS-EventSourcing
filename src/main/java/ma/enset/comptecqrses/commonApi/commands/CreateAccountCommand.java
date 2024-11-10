package ma.enset.comptecqrses.commonApi.commands;

import lombok.Getter;

@Getter
public class CreateAccountCommand extends BaseCommand<String> {
    public Double initialBalance;
    private String currency;

    public CreateAccountCommand(String id, Double initialBalance, String currency) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
    }
}
