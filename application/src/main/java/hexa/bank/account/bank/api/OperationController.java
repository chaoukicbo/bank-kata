package hexa.bank.account.bank.api;


import hexa.bank.account.bank.api.request.OperationRequest;
import hexa.bank.account.bank.commun.exception.AccountNotFoundException;
import hexa.bank.account.bank.commun.exception.AmountException;
import hexa.bank.account.bank.commun.exception.InsufficientFunds;
import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.model.Statement;
import hexa.bank.account.bank.port.in.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/bank/operation")
public class OperationController {

    final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @CrossOrigin
    @PostMapping(path = "/deposit")
    public ResponseEntity<Statement> deposit(@RequestBody OperationRequest operationRequest) throws AmountException, InvalidArgumentException, AccountNotFoundException {
        var statement = operationService.deposit(operationRequest.getClientNumber(), operationRequest.getAccountNumber(), operationRequest.getAmount());
        return ResponseEntity.ok(statement);
    }

    @CrossOrigin
    @PostMapping(path = "/withdrawal")
    public ResponseEntity<Statement> withdrawal(@RequestBody OperationRequest operationRequest) throws AmountException, InvalidArgumentException, AccountNotFoundException, InsufficientFunds {
        var statement = operationService.withdrawal(operationRequest.getClientNumber(), operationRequest.getAccountNumber(), operationRequest.getAmount());
        return ResponseEntity.ok(statement);
    }
}
