package hexa.bank.account.bank.service;

import hexa.bank.account.bank.commun.exception.AccountNotFoundException;
import hexa.bank.account.bank.commun.exception.AmountException;
import hexa.bank.account.bank.commun.exception.InsufficientFunds;
import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.model.Operation;
import hexa.bank.account.bank.model.Statement;
import hexa.bank.account.bank.port.in.OperationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OperationServiceIntegrationTest {

    @Autowired
    OperationService operationService;

    @Test
    void shouldDepositAndReturnStatement() throws InvalidArgumentException, AmountException, AccountNotFoundException {
        Statement statement = operationService.deposit(111111, 1313131, 250.10);

        assertEquals(Operation.DEPOSIT, statement.getOperation());
        assertEquals(250.10, statement.getAmount());
        assertEquals(450.1, statement.getBalance());
        assertNotNull(statement.getDate());
        assertNotNull(statement.getAccount());
        assertEquals(1313131, statement.getAccount().getNumber());
        assertEquals(111111, statement.getAccount().getClient().getNumber());

        assertDoesNotThrow(() -> operationService.deposit(111111, 1313131, 250.10));
    }

    @Test
    void shouldWithdrawalAndReturnStatement() throws InvalidArgumentException, AmountException, AccountNotFoundException, InsufficientFunds {
        Statement statement = operationService.withdrawal(444444, 5555555, 600D);

        assertEquals(Operation.WITHDRAWAL, statement.getOperation());
        assertEquals(600D, statement.getAmount());
        assertEquals(5064.0, statement.getBalance());
        assertNotNull(statement.getDate());
        assertNotNull(statement.getAccount());
        assertEquals(5555555, statement.getAccount().getNumber());
        assertEquals(444444, statement.getAccount().getClient().getNumber());

        assertDoesNotThrow(() -> operationService.withdrawal(444444, 5555555, 600D));
    }
}
