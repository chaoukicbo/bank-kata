package hexa.bank.account.bank.service;

import hexa.bank.account.bank.commun.exception.AccountNotFoundException;
import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.port.out.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OperationServiceImplTest {

    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    OperationServiceImpl operationServiceImpl;

    @Test
    void shouldThrowInvalidArgumentExceptionWhenTryToDepositWithNullArguments() throws InvalidArgumentException, AccountNotFoundException {
        var throwable = catchThrowable(() -> operationServiceImpl.deposit(1, 1,null));
        assertNotNull(throwable);
        assertEquals(throwable.getClass(), InvalidArgumentException.class);
        assertEquals("amount must be not null", throwable.getMessage());
        verify(accountRepository, never()).findAccountByAccountNumberAndClientNumber(Mockito.anyInt(), Mockito.anyInt());

        throwable = catchThrowable(() -> operationServiceImpl.deposit(1, null,5D));
        assertNotNull(throwable);
        assertEquals(throwable.getClass(), InvalidArgumentException.class);
        assertEquals("account number must be not null", throwable.getMessage());
        verify(accountRepository, never()).findAccountByAccountNumberAndClientNumber(Mockito.anyInt(), Mockito.anyInt());

        throwable = catchThrowable(() -> operationServiceImpl.deposit(null, 1,50D));
        assertNotNull(throwable);
        assertEquals(throwable.getClass(), InvalidArgumentException.class);
        assertEquals("client number must be not null", throwable.getMessage());
        verify(accountRepository, never()).findAccountByAccountNumberAndClientNumber(Mockito.anyInt(), Mockito.anyInt());
    }

    @Test
    void shouldThrowInvalidArgumentExceptionWhenTryToWithdrawalWithNullArguments() throws InvalidArgumentException, AccountNotFoundException {
        var throwable = catchThrowable(() -> operationServiceImpl.withdrawal(1, 1,null));
        assertNotNull(throwable);
        assertEquals(throwable.getClass(), InvalidArgumentException.class);
        assertEquals("amount must be not null", throwable.getMessage());
        verify(accountRepository, never()).findAccountByAccountNumberAndClientNumber(Mockito.anyInt(), Mockito.anyInt());

        throwable = catchThrowable(() -> operationServiceImpl.withdrawal(1, null,5D));
        assertNotNull(throwable);
        assertEquals(throwable.getClass(), InvalidArgumentException.class);
        assertEquals("account number must be not null", throwable.getMessage());
        verify(accountRepository, never()).findAccountByAccountNumberAndClientNumber(Mockito.anyInt(), Mockito.anyInt());

        throwable = catchThrowable(() -> operationServiceImpl.withdrawal(null, 1,50D));
        assertNotNull(throwable);
        assertEquals(throwable.getClass(), InvalidArgumentException.class);
        assertEquals("client number must be not null", throwable.getMessage());
        verify(accountRepository, never()).findAccountByAccountNumberAndClientNumber(Mockito.anyInt(), Mockito.anyInt());
    }
}