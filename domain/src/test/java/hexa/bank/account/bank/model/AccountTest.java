package hexa.bank.account.bank.model;

import hexa.bank.account.bank.commun.exception.AmountException;
import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class AccountTest {

    @Test
    void shouldMakeADeposit() throws InvalidArgumentException, AmountException {
        Account account = new Account(1, 100D, new Client());

        account.deposit(10D);
        assertEquals(110D, account.getBalance());

        account.deposit(11.5);
        account.deposit(50.8);
        assertEquals(172.3, account.getBalance());
    }



    @Test
    void shouldThrowInvalidArgumentExceptionWhenTryToDepositWithNullAmount() {
        assertThrowsExactly(InvalidArgumentException.class,
                (() -> new Account(1, 100D, new Client()).deposit(null)),
                "amount must be not null");
    }


    @Test
    void shouldThrowAmountExceptionWhenTryToDepositWithNonValidAmount() {
        assertThrowsExactly(AmountException.class,
                () -> new Account(1, 100D, new Client()).deposit(-1.0),
                "amount must be greater than : 0.0");

        assertThrowsExactly(AmountException.class,
                () -> new Account(1, 100D, new Client()).deposit(10006.0),
                "amount must be less or equal to : 10000.0");
    }
}