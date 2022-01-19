package hexa.bank.account.bank.model;


import hexa.bank.account.bank.commun.exception.AmountException;
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
