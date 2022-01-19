package hexa.bank.account.bank.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OperationRequest {
    private final Integer clientNumber;
    private final Integer accountNumber;
    private final Double amount;

    public OperationRequest(@JsonProperty("clientNumber") final Integer clientNumber,
                            @JsonProperty("accountNumber") final Integer accountNumber,
                            @JsonProperty("amount") final Double amount) {
        this.clientNumber = clientNumber;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public Integer getClientNumber() {
        return clientNumber;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

}
