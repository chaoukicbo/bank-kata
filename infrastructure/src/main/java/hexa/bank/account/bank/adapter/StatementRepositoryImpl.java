package hexa.bank.account.bank.adapter;


import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.commun.exception.StatementNotFoundException;
import hexa.bank.account.bank.commun.utils.Validation;
import hexa.bank.account.bank.mapper.StatementMapper;
import hexa.bank.account.bank.model.Statement;
import hexa.bank.account.bank.port.out.StatementRepository;
import hexa.bank.account.bank.repository.StatementJpaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("statementRepository")
public class StatementRepositoryImpl implements StatementRepository {

    final StatementJpaRepository statementJpaRepository;

    public StatementRepositoryImpl(StatementJpaRepository statementJpaRepository) {
        this.statementJpaRepository = statementJpaRepository;
    }


    @Override
    public List<Statement> findAllByClientNumberAndAccountNumber(Integer clientNumber, Integer accountNumber) throws StatementNotFoundException, InvalidArgumentException {
        Validation.assertNonNull(clientNumber, "client number must be not null");
        Validation.assertNonNull(accountNumber, "account number must be not null");

        var statementEntity = statementJpaRepository.findAllByAccountOrderByIdDesc(clientNumber, accountNumber);
        if (statementEntity.isEmpty()) {
            throw new StatementNotFoundException();
        }
        return statementEntity.stream().map(StatementMapper::mapToStatement).collect(Collectors.toList());
    }
}
