package hexa.bank.account.bank.adapter;


import hexa.bank.account.bank.commun.exception.AccountNotFoundException;
import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.commun.utils.Validation;
import hexa.bank.account.bank.entity.AccountEntity;
import hexa.bank.account.bank.entity.StatementEntity;
import hexa.bank.account.bank.mapper.AccountMapper;
import hexa.bank.account.bank.mapper.StatementMapper;
import hexa.bank.account.bank.model.Account;
import hexa.bank.account.bank.model.Operation;
import hexa.bank.account.bank.model.Statement;
import hexa.bank.account.bank.port.out.AccountRepository;
import hexa.bank.account.bank.repository.AccountJpaRepository;
import hexa.bank.account.bank.repository.StatementJpaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;


@Service
@Qualifier("accountRepository")
public class AccountRepositoryImpl implements AccountRepository {

    final AccountJpaRepository accountJpaRepository;
    final StatementJpaRepository statementJpaRepository;

    public AccountRepositoryImpl(AccountJpaRepository accountJpaRepository, StatementJpaRepository statementJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
        this.statementJpaRepository = statementJpaRepository;
    }

    @Override
    public Account findAccountByAccountNumberAndClientNumber(Integer accountNumber, Integer clientNumber) throws AccountNotFoundException, InvalidArgumentException {
        Validation.assertNonNull(clientNumber, "client number must be not null");
        Validation.assertNonNull(accountNumber, "account number must be not null");

        Optional<AccountEntity> optional = accountJpaRepository.findByAccountNumberAndClientNumber(accountNumber, clientNumber);
        if (optional.isEmpty()) {
            throw new AccountNotFoundException();
        }
        return AccountMapper.mapToAccount(optional.get());
    }

    @Override
    @Transactional
    public Statement updateAccountAndSaveStatement(Account account, Operation operation, Double amount) throws AccountNotFoundException, InvalidArgumentException {
        Validation.assertNonNull(account, "account number must be not null");
        Validation.assertNonNull(operation, "operation must be not null");
        Validation.assertNonNull(amount, "amount must be not null");

        AccountEntity accountEntity = updateAccount(account);

        return saveStatement(operation, amount, accountEntity);
    }

    private AccountEntity updateAccount(Account account) throws AccountNotFoundException {
        var optional = accountJpaRepository.findByNumber(account.getNumber());
        if (optional.isEmpty()) {
            throw new AccountNotFoundException();
        }
        var accountEntity = optional.get();
        accountEntity.setBalance(account.getBalance());
        return accountEntity;
    }

    private Statement saveStatement(Operation operation, Double amount, AccountEntity accountEntity) {
        var statementToSave = new StatementEntity(StatementMapper.mapToOperationType(operation), LocalDate.now(),
                amount, accountEntity.getBalance(), accountEntity);
        var statementEntity = statementJpaRepository.save(statementToSave);
        return StatementMapper.mapToStatement(statementEntity);
    }
}
