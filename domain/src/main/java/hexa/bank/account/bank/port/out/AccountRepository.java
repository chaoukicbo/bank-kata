package hexa.bank.account.bank.port.out;


import hexa.bank.account.bank.commun.exception.AccountNotFoundException;
import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.model.Account;
import hexa.bank.account.bank.model.Operation;
import hexa.bank.account.bank.model.Statement;

public interface AccountRepository {

    /**
     * Retrieve Account from database by number
     *
     * @param accountNumber the number of the account to retrieve must be not null
     * @param clientNumber  the client number having the account must be not null
     * @return Account if it is found
     * @throws AccountNotFoundException if no Account found
     * @throws InvalidArgumentException if any argument is null
     */
    Account findAccountByAccountNumberAndClientNumber(Integer accountNumber, Integer clientNumber) throws AccountNotFoundException, InvalidArgumentException;

    /**
     * Update the amount of an account and save statement with the operation type
     * @param account the account to update
     * @param operation the operation type
     * @param amount th amount of the operation
     * @return new Statement to confirm operation
     * @throws AccountNotFoundException if no Account found
     * @throws InvalidArgumentException if any argument is null
     */
    Statement updateAccountAndSaveStatement(Account account, Operation operation, Double amount) throws AccountNotFoundException, InvalidArgumentException;
}
