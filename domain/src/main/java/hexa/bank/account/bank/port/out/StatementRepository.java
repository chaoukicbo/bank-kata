package hexa.bank.account.bank.port.out;



import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.commun.exception.StatementNotFoundException;
import hexa.bank.account.bank.model.Statement;

import java.util.List;

public interface StatementRepository {

    /**
     *
     * @param clientNumber the client number must be not null
     * @param accountNumber the account number must be not null
     * @return List<Statement> list of history operation
     * @throws StatementNotFoundException if no history found
     * @throws InvalidArgumentException if any argument is null
     */
    List<Statement> findAllByClientNumberAndAccountNumber(Integer clientNumber, Integer accountNumber) throws StatementNotFoundException, InvalidArgumentException;
}
