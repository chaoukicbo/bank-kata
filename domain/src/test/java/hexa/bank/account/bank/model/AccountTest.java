package hexa.bank.account.bank.model;

import hexa.bank.account.bank.commun.exception.AmountException;
import hexa.bank.account.bank.commun.exception.InsufficientFunds;
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
    void shouldMakeAWithdrawal() throws InvalidArgumentException, AmountException, InsufficientFunds {
        Account account = new Account(1, 100D, new Client());

        account.withdrawal(10D);
        assertEquals(90.0, account.getBalance());

        account.withdrawal(11.5);
        account.withdrawal(50D);
        assertEquals(28.5, account.getBalance());
    }

    @Test
    void shouldThrowInvalidArgumentExceptionWhenTryToDepositOrWithdrawalWithNullAmount() {
        assertThrowsExactly(InvalidArgumentException.class,
                () -> new Account(1, 100D, new Client()).withdrawal(null),
                "amount must be not null");

        assertThrowsExactly(InvalidArgumentException.class,
                (() -> new Account(1, 100D, new Client()).deposit(null)),
                "amount must be not null");
    }

    @Test
    void shouldThrowAmountExceptionWhenTryToWithdrawalWithNonValidAmount() {
        assertThrowsExactly(AmountException.class,
                () -> new Account(1, 100D, new Client()).withdrawal(-1.0),
                "amount must be greater than : 0.0");

        assertThrowsExactly(AmountException.class,
                () -> new Account(1, 100D, new Client()).withdrawal(2005.0),
                "amount must be less or equal to : 2000.0");

        assertThrowsExactly(InsufficientFunds.class,
                () -> new Account(1, 100D, new Client()).withdrawal(150D),
                "Insufficient Funds in your account : your account balance is 100.0");
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