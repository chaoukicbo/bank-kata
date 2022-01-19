package hexa.bank.account.bank.service;


import hexa.bank.account.bank.commun.exception.AccountNotFoundException;
import hexa.bank.account.bank.commun.exception.AmountException;
import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.commun.utils.Validation;
import hexa.bank.account.bank.model.Account;
import hexa.bank.account.bank.model.Operation;
import hexa.bank.account.bank.model.Statement;
import hexa.bank.account.bank.port.in.OperationService;
import hexa.bank.account.bank.port.out.AccountRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("operationService")
public class OperationServiceImpl implements OperationService {

    final AccountRepository accountRepository;

    public OperationServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Statement deposit(Integer clientNumber, Integer accountNumber, Double amount) throws InvalidArgumentException, AccountNotFoundException, AmountException {
        var account = retrieveAccount(clientNumber, accountNumber, amount);
        account.deposit(amount);

        return accountRepository.updateAccountAndSaveStatement(account, Operation.DEPOSIT, amount);
    }

    private Account retrieveAccount(Integer clientNumber, Integer accountNumber, Double amount) throws InvalidArgumentException, AccountNotFoundException {
        Validation.assertNonNull(clientNumber, "client number must be not null");
        Validation.assertNonNull(accountNumber, "account number must be not null");
        Validation.assertNonNull(amount, "amount must be not null");
        return accountRepository.findAccountByAccountNumberAndClientNumber(accountNumber, clientNumber);
    }
}
