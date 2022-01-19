package hexa.bank.account.bank.commun.exception;

public class ClientNotFoundException extends Exception {

    public ClientNotFoundException() {
        super("the client number is not correct");
    }
}
