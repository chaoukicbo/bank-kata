package hexa.bank.account.bank.port.in;


import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.commun.exception.StatementNotFoundException;
import hexa.bank.account.bank.model.Statement;

import java.util.List;

public interface OperationHistoryService {

    /**
     * @param clientNumber  the client number must be not null
     * @param accountNumber the account number corresponding to the client must be not null
     * @return List<Statement> a history of account operations
     * @throws InvalidArgumentException   if any argument is null
     * @throws StatementNotFoundException if no statements (history) found corresponding to @param accountNumber and @param clientNumber
     */
    List<Statement> history(Integer clientNumber, Integer accountNumber) throws InvalidArgumentException, StatementNotFoundException;
}
