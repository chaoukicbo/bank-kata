package hexa.bank.account.bank.commun.exception;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException() {
        super("Account not found please check your account and client numbers");
    }
}