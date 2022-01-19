package hexa.bank.account.bank.mapper;


import hexa.bank.account.bank.entity.AccountEntity;
import hexa.bank.account.bank.model.Account;

public class AccountMapper {

    private AccountMapper() {
        super();
    }

    public static Account mapToAccount(AccountEntity accountEntity) {
        return new Account(accountEntity.getNumber(), accountEntity.getBalance(), ClientMapper.mapToClient(accountEntity.getClient()));
    }
}
