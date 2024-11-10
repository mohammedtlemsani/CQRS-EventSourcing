package ma.enset.comptecqrses.commonApi.commands;

public class DebitAccountCommand extends BaseCommand<String> {
    public Double amount;
    private String currency;

    public DebitAccountCommand(String id, Double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
