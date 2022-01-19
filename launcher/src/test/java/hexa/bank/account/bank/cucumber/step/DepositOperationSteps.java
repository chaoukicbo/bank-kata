package hexa.bank.account.bank.cucumber.step;

import hexa.bank.account.bank.commun.exception.*;
import hexa.bank.account.bank.model.Operation;
import hexa.bank.account.bank.model.Statement;
import hexa.bank.account.bank.port.in.OperationHistoryService;
import hexa.bank.account.bank.service.OperationServiceImpl;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DepositOperationSteps {
    @Autowired
    OperationServiceImpl operationService;
    @Autowired
    OperationHistoryService operationHistoryService;

    private Integer clientNumber;
    private Integer wrongClientNumber;
    private Integer accountNumber;
    private Statement statement;
    private Double operationAmount;
    private Integer wrongAccountNumber;
    private String operationType;

    @Given("the following client number {int}")
    public void theFollowingClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    @And("with the following account number {int}")
    public void withFollowingAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    @When("I make a deposit in my account with an amount of {double}")
    public void iWantToMakeADepositInMyAccountOf(double amount) throws InvalidArgumentException, AmountException, AccountNotFoundException {
        this.statement = operationService.deposit(this.clientNumber, this.accountNumber, amount);
        Assertions.assertThat(statement).isNotNull();
        Assertions.assertThat(statement.getOperation()).isEqualTo(Operation.DEPOSIT);
        Assertions.assertThat(statement.getAmount()).isEqualTo(amount);
        Assertions.assertThat(statement.getAccount()).isNotNull();
        Assertions.assertThat(statement.getAccount().getNumber()).isEqualTo(this.accountNumber);

        var client = statement.getAccount().getClient();
        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getNumber()).isEqualTo(this.clientNumber);
    }

    @Then("my account balance will be equal to {double}")
    public void myAccountBalanceWillIncreaseBy(double balance) {
        Assertions.assertThat(statement.getBalance()).isEqualTo(balance);
        Assertions.assertThat(statement.getAccount().getBalance()).isEqualTo(balance);
    }

    @Given("the following wrong client number {int}")
    public void theFollowingNoneClientNumber(int noneClientNumber) {
        this.wrongClientNumber = noneClientNumber;
    }

    @And("with the wrong following account number {int}")
    public void withTheWrongFollowingAccountNumber(int wrongAccountNumber) {
        this.wrongAccountNumber = wrongAccountNumber;
    }

    @When("I try to make a {word} in my account with an amount of {double}")
    public void iTryToMakeADepositInMyAccountWithAnAmountOf(String operation, double amount) {
        Assertions.assertThat(operation).isIn(Operation.WITHDRAWAL.toString(), Operation.DEPOSIT.toString());
        this.operationAmount = amount;
        this.operationType = operation;
    }

    @Then("i will have the following exception {word} with the following message : {string}")
    public void iWillHaveTheFollowingExceptionClientNotFoundExceptionException(String exceptionClassName, String message) {
        var throwable = catchThrowable(this::makeOperation);
        assertNotNull(throwable);
        assertEquals(exceptionClassName, throwable.getClass().getSimpleName());
        assertEquals(message, throwable.getMessage());
    }

    @When("I make a withdrawal in my account with an amount of {double}")
    public void iMakeAWithdrawalInMyAccountWithAnAmountOf(double amount) throws InvalidArgumentException, AmountException, AccountNotFoundException, InsufficientFunds {
        this.statement = operationService.withdrawal(this.clientNumber, this.accountNumber, amount);
        Assertions.assertThat(statement).isNotNull();
        Assertions.assertThat(statement.getOperation()).isEqualTo(Operation.WITHDRAWAL);
        Assertions.assertThat(statement.getAmount()).isEqualTo(amount);
        Assertions.assertThat(statement.getAccount()).isNotNull();
        Assertions.assertThat(statement.getAccount().getNumber()).isEqualTo(this.accountNumber);

        var client = statement.getAccount().getClient();
        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getNumber()).isEqualTo(this.clientNumber);
    }

    private void makeOperation() throws InvalidArgumentException, AmountException, AccountNotFoundException, InsufficientFunds {
        var client = Objects.isNull(this.wrongClientNumber) ? this.clientNumber : this.wrongClientNumber;
        var account = Objects.isNull(this.wrongAccountNumber) ? this.accountNumber : this.wrongAccountNumber;
        if (this.operationType.equals(Operation.DEPOSIT.toString())) {
            operationService.deposit(client, account, operationAmount);
        }
        if (this.operationType.equals(Operation.WITHDRAWAL.toString())) {
            operationService.withdrawal(client, account, operationAmount);
        }
    }

    @And("my history of operations will be the following statements$")
    public void myHistoryWillBe(final List<Statement> statements) throws StatementNotFoundException, InvalidArgumentException {
        var historyOfStatements = operationHistoryService.history(this.clientNumber, this.accountNumber);

        Assertions.assertThat(statements).hasSize(historyOfStatements.size());
        Assertions.assertThat(statements).extracting("operation")
                .containsExactly(historyOfStatements.stream().map(Statement::getOperation).toArray());
        Assertions.assertThat(statements).extracting("amount")
                .containsExactly(historyOfStatements.stream().map(Statement::getAmount).toArray());
        Assertions.assertThat(statements).extracting("balance")
                .containsExactly(historyOfStatements.stream().map(Statement::getBalance).toArray());

    }
}
