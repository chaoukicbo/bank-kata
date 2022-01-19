package hexa.bank.account.bank.adapter;

import hexa.bank.account.bank.adapter.data.DataUtils;
import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.commun.exception.StatementNotFoundException;
import hexa.bank.account.bank.repository.StatementJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StatementRepositoryImplTest {

    @Mock
    StatementJpaRepository statementJpaRepository;
    @InjectMocks
    StatementRepositoryImpl statementRepository;

    @DisplayName("Get statements by client number and account number")
    @Test
    void shouldGetAccountByNumber() throws StatementNotFoundException, InvalidArgumentException {
        var statementEntity = DataUtils.getStatementEntity();
        Mockito.when(statementJpaRepository.findAllByAccountOrderByIdDesc(Mockito.any(), Mockito.any()))
                .thenReturn(List.of(statementEntity));
        var statements = statementRepository.findAllByClientNumberAndAccountNumber(1,1);

        verify(statementJpaRepository).findAllByAccountOrderByIdDesc(1, 1);

        assertNotNull(statements);
        assertEquals(1, statements.size());
    }


    @DisplayName("Should Throw Invalid Argument Exception When Try To Update Account And Save Statement")
    @Test
    void shouldThrowInvalidArgumentExceptionWhenTryToUpdateAccountAndSaveStatement() {
        var throwable = catchThrowable(() -> statementRepository.findAllByClientNumberAndAccountNumber(null,1));
        verify(statementJpaRepository, never()).findAllByAccountOrderByIdDesc(null, 1);
        assertNotNull(throwable);
        assertEquals(InvalidArgumentException.class, throwable.getClass());
        assertEquals("client number must be not null", throwable.getMessage());

        throwable = catchThrowable(() -> statementRepository.findAllByClientNumberAndAccountNumber(1,null));
        verify(statementJpaRepository, never()).findAllByAccountOrderByIdDesc(null, 1);
        assertNotNull(throwable);
        assertEquals(InvalidArgumentException.class, throwable.getClass());
        assertEquals("account number must be not null", throwable.getMessage());
    }
}