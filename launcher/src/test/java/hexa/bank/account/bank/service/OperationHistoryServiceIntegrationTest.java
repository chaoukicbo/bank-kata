package hexa.bank.account.bank.service;

import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.commun.exception.StatementNotFoundException;
import hexa.bank.account.bank.model.Operation;
import hexa.bank.account.bank.port.in.OperationHistoryService;
import hexa.bank.account.bank.port.in.OperationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OperationHistoryServiceIntegrationTest {


    @Autowired
    OperationHistoryService operationHistoryService;
    @Autowired
    OperationService operationService;

    @Test
    void shouldWithdrawalAndReturnStatement() throws StatementNotFoundException, InvalidArgumentException {
        assertDoesNotThrow(() -> operationService.deposit(444444, 6666666, 600D));
        assertDoesNotThrow(() -> operationService.deposit(444444, 6666666, 50D));
        assertDoesNotThrow(() -> operationService.withdrawal(444444, 6666666, 150D));

        var statements = operationHistoryService.history(444444, 6666666);

        assertEquals(3, statements.size());
        assertThat(statements).extracting("operation").containsExactly(Operation.WITHDRAWAL, Operation.DEPOSIT, Operation.DEPOSIT);
        assertThat(statements).extracting("amount").containsExactly(150D, 50D, 600D);
        assertThat(statements).extracting("balance").containsExactly(10500D, 10650D, 10600D);
    }

}
