package hexa.bank.account.bank.api.exception;

import hexa.bank.account.bank.commun.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class OperationExceptionHandler {


    @ExceptionHandler(value = {AccountNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleAccountNotFoundException(AccountNotFoundException ex) {
        log.error("Account Not Found Exception : {}", ex.getMessage());
        return new ResponseEntity<>(new ExceptionResponse("Account Not Found Exception", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidArgumentException.class})
    public ResponseEntity<ExceptionResponse> handleInvalidArgumentException(InvalidArgumentException ex) {
        log.error("Invalid Argument Exception : {}", ex.getMessage());
        return new ResponseEntity<>(new ExceptionResponse("Invalid Argument Exception", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AmountException.class})
    public ResponseEntity<ExceptionResponse> handleAmountException(AmountException ex) {
        log.error("Amount Exception : {}", ex.getMessage());
        return new ResponseEntity<>(new ExceptionResponse("Amount Exception", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
