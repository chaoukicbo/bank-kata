package hexa.bank.account.bank.port.in;


import hexa.bank.account.bank.commun.exception.AccountNotFoundException;
import hexa.bank.account.bank.commun.exception.AmountException;
import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.model.Statement;

public interface OperationService {

    /**
     * Make a deposit in an account
     *
     * @param clientNumber  the client number must be not null
     * @param accountNumber the account number corresponding to the client must be not null
     * @param amount        the amount of operation must be not null
     * @return Statement after saving operation containing operation type, amount of operation and the new balance
     * @throws InvalidArgumentException if any argument is null
     * @throws AccountNotFoundException if no Account is found corresponding to @param accountNumber and @param clientNumber
     * @throws AmountException          if amount is less than the minimum authorized or greater than the maximum authorized
     */
    Statement deposit(Integer clientNumber, Integer accountNumber, Double amount) throws InvalidArgumentException, AccountNotFoundException, AmountException;

}