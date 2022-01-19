package hexa.bank.account.bank.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoryRequest {

    private final Integer clientNumber;
    private final Integer accountNumber;

    public HistoryRequest(@JsonProperty("clientNumber") final Integer clientNumber, @JsonProperty("accountNumber") final Integer accountNumber) {
        this.clientNumber = clientNumber;
        this.accountNumber = accountNumber;
    }

    public Integer getClientNumber() {
        return clientNumber;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }
}
