package hexa.bank.account.bank.model;


import hexa.bank.account.bank.commun.exception.AmountException;
import hexa.bank.account.bank.commun.exception.InsufficientFunds;
import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.commun.rules.AmountRules;

import java.util.Objects;


public class Account {

    private Integer number;
    private Double balance;
    private Client client;

    public Account() {
        super();
    }

    public Account(Integer number, Double balance, Client client) {
        this.number = number;
        this.balance = balance;
        this.client = client;
    }

    public void deposit(Double amount) throws AmountException, InvalidArgumentException {
        checkAmountBeforeDeposit(amount);
        this.balance += amount;
    }

    public void withdrawal(Double amount) throws AmountException, InsufficientFunds, InvalidArgumentException {
        checkAmountBeforeWithdrawal(amount);
        this.balance -= amount;
    }

    private void checkAmountBeforeDeposit(Double amount) throws AmountException, InvalidArgumentException {
        if (Objects.isNull(amount)) {
            throw new InvalidArgumentException("amount must be not null");
        }
        if (amount <= AmountRules.MIN_DEPOSIT_AMOUNT) {
            throw new AmountException("amount must be greater than : " + AmountRules.MIN_DEPOSIT_AMOUNT);
        }
        if (amount > AmountRules.MAX_DEPOSIT_AMOUNT) {
            throw new AmountException("amount must be less or equal to : " + AmountRules.MAX_DEPOSIT_AMOUNT);
        }
    }

    private void checkAmountBeforeWithdrawal(Double amount) throws AmountException, InsufficientFunds, InvalidArgumentException {
        if (Objects.isNull(amount)) {
            throw new InvalidArgumentException("amount must be not null");
        }
        if (amount <= AmountRules.MIN_WITHDRAWAL_DEPOSIT) {
            throw new AmountException("amount must be greater than : " + AmountRules.MIN_WITHDRAWAL_DEPOSIT);
        }
        if (amount > AmountRules.MAX_WITHDRAWAL_AMOUNT) {
            throw new AmountException("amount must be less or equal to : " + AmountRules.MAX_WITHDRAWAL_AMOUNT);
        }
        if (amount > this.balance) {
            throw new InsufficientFunds(balance);
        }
    }

    public Integer getNumber() {
        return number;
    }

    public Double getBalance() {
        return balance;
    }

    public Client getClient() {
        return client;
    }
}
