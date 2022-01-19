package hexa.bank.account.bank.adapter;

import hexa.bank.account.bank.adapter.data.DataUtils;
import hexa.bank.account.bank.commun.exception.AccountNotFoundException;
import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.entity.AccountEntity;
import hexa.bank.account.bank.entity.StatementEntity;
import hexa.bank.account.bank.mapper.AccountMapper;
import hexa.bank.account.bank.model.Operation;
import hexa.bank.account.bank.repository.AccountJpaRepository;
import hexa.bank.account.bank.repository.StatementJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountRepositoryImplTest {

    @Mock
    AccountJpaRepository accountJpaRepository;
    @Mock
    StatementJpaRepository statementJpaRepository;
    @InjectMocks
    AccountRepositoryImpl accountRepositoryImpl;


    @DisplayName("Get account by number")
    @Test
    void shouldGetAccountByNumber()  throws InvalidArgumentException, AccountNotFoundException {

        AccountEntity accountEntity = DataUtils.getAccountEntity();
        Mockito.when(accountJpaRepository.findByAccountNumberAndClientNumber(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.of(accountEntity));
        var account = accountRepositoryImpl.findAccountByAccountNumberAndClientNumber(1,1);

        verify(accountJpaRepository).findByAccountNumberAndClientNumber(1, 1);

        assertNotNull(account);
        assertEquals(accountEntity.getBalance(), account.getBalance());
        assertEquals(accountEntity.getNumber(), account.getNumber());

        assertNotNull(account.getClient());
        assertEquals(accountEntity.getClient().getNumber(), account.getClient().getNumber());
        assertEquals(accountEntity.getClient().getFirstName(), account.getClient().getFirstName());
        assertEquals(accountEntity.getClient().getLastName(), account.getClient().getLastName());
    }

    @DisplayName("Should throw InvalidArgumentException when try get account by null argument")
    @Test
    void shouldThrowInvalidArgumentExceptionWhenTryToGetAccount() {
        assertThrowsExactly(InvalidArgumentException.class,
                () -> accountRepositoryImpl.findAccountByAccountNumberAndClientNumber(1, null),
                "client number must be not null");
        verify(accountJpaRepository, never()).findByAccountNumberAndClientNumber(Mockito.anyInt(), Mockito.anyInt());

        assertThrowsExactly(InvalidArgumentException.class,
                () -> accountRepositoryImpl.findAccountByAccountNumberAndClientNumber(null, 1),
                "account number must be not null");
        verify(accountJpaRepository, never()).findByAccountNumberAndClientNumber(Mockito.anyInt(), Mockito.anyInt());
    }

    @DisplayName("Should throw AccountNotFoundException when try get account does not exist")
    @Test
    void shouldThrowAccountNotFoundExceptionWhenTryToGetAccount() {
        Mockito.when(accountJpaRepository.findByAccountNumberAndClientNumber(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(Optional.empty());

        assertThrowsExactly(AccountNotFoundException.class,
                () -> accountRepositoryImpl.findAccountByAccountNumberAndClientNumber(1, 3),
                "Account not found please check your account and client numbers");
        verify(accountJpaRepository).findByAccountNumberAndClientNumber(Mockito.anyInt(), Mockito.anyInt());
    }

    @DisplayName("Update Account And Save Statement")
    @Test
    void shouldUpdateAccountAndSaveStatement() throws InvalidArgumentException, AccountNotFoundException {
        AccountEntity accountEntity = DataUtils.getAccountEntity();
        Mockito.when(accountJpaRepository.findByNumber(Mockito.any())).thenReturn(Optional.of(accountEntity));

        StatementEntity statementEntity = DataUtils.getStatementEntity();
        Mockito.when(statementJpaRepository.save(Mockito.any())).thenReturn(statementEntity);


        var statement = accountRepositoryImpl.updateAccountAndSaveStatement(AccountMapper.mapToAccount(DataUtils.getAccountEntity()), Operation.DEPOSIT, 100D);

        verify(accountJpaRepository).findByNumber(1);
        verify(statementJpaRepository).save(Mockito.any());

        assertNotNull(statement);
        assertEquals(statementEntity.getDate(), statement.getDate());
        assertEquals(statementEntity.getBalance(), statement.getBalance());
        assertEquals(statementEntity.getAmount(), statement.getAmount());
        assertEquals(Operation.DEPOSIT, statement.getOperation());
        assertEquals(statementEntity.getAccount().getNumber(), statement.getAccount().getNumber());
    }

    @DisplayName("Should Throw Account Not Found Exception When Try To Update A Non Existing Account")
    @Test
    void shouldThrowAccountNotFoundExceptionWhenTryToUpdateANonExistingAccount() {
        Mockito.when(accountJpaRepository.findByNumber(Mockito.any())).thenReturn(Optional.empty());
        var throwable = catchThrowable(() -> accountRepositoryImpl.updateAccountAndSaveStatement(AccountMapper.mapToAccount(DataUtils.getAccountEntity()), Operation.DEPOSIT, 100D));

        verify(accountJpaRepository).findByNumber(1);
        verify(statementJpaRepository, never()).save(Mockito.any());

        assertNotNull(throwable);
        assertEquals(AccountNotFoundException.class, throwable.getClass());
        assertEquals("Account not found please check your account and client numbers", throwable.getMessage());
    }

    @DisplayName("Should Throw Invalid Argument Exception When Try To Update Account And Save Statement")
    @Test
    void shouldThrowInvalidArgumentExceptionWhenTryToUpdateAccountAndSaveStatement() {
        var throwable = catchThrowable(() -> accountRepositoryImpl.updateAccountAndSaveStatement(AccountMapper.mapToAccount(DataUtils.getAccountEntity()), Operation.DEPOSIT, null));
        verify(accountJpaRepository, never()).findByNumber(1);
        verify(statementJpaRepository, never()).save(Mockito.any());
        assertNotNull(throwable);
        assertEquals(InvalidArgumentException.class, throwable.getClass());
        assertEquals("amount must be not null", throwable.getMessage());


        throwable = catchThrowable(() -> accountRepositoryImpl.updateAccountAndSaveStatement(AccountMapper.mapToAccount(DataUtils.getAccountEntity()), null, 100D));
        verify(accountJpaRepository, never()).findByNumber(1);
        verify(statementJpaRepository, never()).save(Mockito.any());
        assertNotNull(throwable);
        assertEquals(InvalidArgumentException.class, throwable.getClass());
        assertEquals("operation must be not null", throwable.getMessage());


        throwable = catchThrowable(() -> accountRepositoryImpl.updateAccountAndSaveStatement(null, Operation.DEPOSIT, 100D));
        verify(accountJpaRepository, never()).findByNumber(1);
        verify(statementJpaRepository, never()).save(Mockito.any());
        assertNotNull(throwable);
        assertEquals(InvalidArgumentException.class, throwable.getClass());
        assertEquals("account number must be not null", throwable.getMessage());
    }


}