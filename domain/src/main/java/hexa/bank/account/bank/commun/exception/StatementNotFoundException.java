package hexa.bank.account.bank.commun.exception;

public class StatementNotFoundException extends Exception {

    public StatementNotFoundException() {
        super("History not found please check your account and client numbers");
    }
}
