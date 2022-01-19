package hexa.bank.account.bank.service;

import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.commun.exception.StatementNotFoundException;
import hexa.bank.account.bank.port.out.StatementRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
class OperationHistoryServiceImplTest {

    @Mock
    StatementRepository statementRepository;
    @InjectMocks
    OperationHistoryServiceImpl operationHistoryServiceImpl;

    @DisplayName("Should throw InvalidArgumentException when try get history with null argument")
    @Test
    void shouldThrowInvalidArgumentExceptionWhenTryToGetHistoryWithNullArguments() throws StatementNotFoundException, InvalidArgumentException {
        assertThrowsExactly(InvalidArgumentException.class,
                () -> operationHistoryServiceImpl.history(1, null),
                "account number must be not null");

        assertThrowsExactly(InvalidArgumentException.class,
                () -> operationHistoryServiceImpl.history(null, 1),
                "client number must be not null");
    }
}