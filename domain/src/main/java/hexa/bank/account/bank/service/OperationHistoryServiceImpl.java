package hexa.bank.account.bank.service;


import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.commun.exception.StatementNotFoundException;
import hexa.bank.account.bank.commun.utils.Validation;
import hexa.bank.account.bank.model.Statement;
import hexa.bank.account.bank.port.in.OperationHistoryService;
import hexa.bank.account.bank.port.out.StatementRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("operationHistoryService")
public class OperationHistoryServiceImpl implements OperationHistoryService {

    final StatementRepository statementRepository;

    public OperationHistoryServiceImpl(StatementRepository statementRepository) {
        this.statementRepository = statementRepository;
    }

    public List<Statement> history(Integer clientNumber, Integer accountNumber) throws InvalidArgumentException, StatementNotFoundException {
        Validation.assertNonNull(clientNumber, "client number must be not null");
        Validation.assertNonNull(accountNumber, "account number must be not null");
        return statementRepository.findAllByClientNumberAndAccountNumber(clientNumber, accountNumber);
    }
}
