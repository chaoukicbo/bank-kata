package hexa.bank.account.bank.api.exception;

public class ExceptionResponse {

    private final String error;
    private final String message;

    public ExceptionResponse(String error, String message) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }
}
