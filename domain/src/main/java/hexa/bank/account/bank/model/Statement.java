package hexa.bank.account.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;


public class Statement {

    private Operation operation;
    private LocalDate date;
    private Double amount;
    private Double balance;
    @JsonIgnore
    private Account account;


    public Statement() {
        super();
    }

    public Statement(Operation operation, LocalDate date, Double amount, Double balance, Account account) {
        this.operation = operation;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
        this.account = account;
    }

    public Operation getOperation() {
        return operation;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getBalance() {
        return balance;
    }

    public Account getAccount() {
        return account;
    }


}
