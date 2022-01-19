package hexa.bank.account.bank.adapter.data;

import hexa.bank.account.bank.entity.AccountEntity;
import hexa.bank.account.bank.entity.ClientEntity;
import hexa.bank.account.bank.entity.OperationType;
import hexa.bank.account.bank.entity.StatementEntity;

import java.time.LocalDate;

public class DataUtils {

    private DataUtils() {
        super();
    }

    public static StatementEntity getStatementEntity() {
        return StatementEntity.builder()
                .id(1L).amount(50D).balance(50D)
                .account(getAccountEntity())
                .date(LocalDate.now())
                .operation(OperationType.D).build();
    }

    public static AccountEntity getAccountEntity() {
        var client = new ClientEntity(1, "Joe", "Ford");
        return new AccountEntity(1L, 1, 100D, client);
    }
}
