package ma.enset.comptecqrses.commonApi.commands;

public class CreditAccountCommand extends BaseCommand<String> {
    public Double amount;
    private String currency;

    public CreditAccountCommand(String id, Double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
