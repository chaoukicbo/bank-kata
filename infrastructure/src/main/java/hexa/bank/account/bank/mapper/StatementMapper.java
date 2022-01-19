package hexa.bank.account.bank.mapper;


import hexa.bank.account.bank.entity.OperationType;
import hexa.bank.account.bank.entity.StatementEntity;
import hexa.bank.account.bank.model.Operation;
import hexa.bank.account.bank.model.Statement;

public class StatementMapper {

    private StatementMapper() {
        super();
    }

    public static Statement mapToStatement(StatementEntity statementEntity) {
        return new Statement(mapToOperation(statementEntity.getOperation()), statementEntity.getDate(), statementEntity.getAmount(), statementEntity.getBalance(), AccountMapper.mapToAccount(statementEntity.getAccount()));
    }

    public static Operation mapToOperation(OperationType operationType) {
        return operationType.equals(OperationType.D) ? Operation.DEPOSIT : Operation.WITHDRAWAL;
    }

    public static OperationType mapToOperationType(Operation operation) {
        return operation.equals(Operation.DEPOSIT) ? OperationType.D : OperationType.W;
    }

}
