package hexa.bank.account.bank.commun.exception;

public class InsufficientFunds extends Exception {

    public InsufficientFunds(Double balance) {
        super("Insufficient Funds in your account : your account balance is " + balance);
    }
}
