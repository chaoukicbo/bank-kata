package hexa.bank.account.bank.api;


import hexa.bank.account.bank.api.request.HistoryRequest;
import hexa.bank.account.bank.commun.exception.InvalidArgumentException;
import hexa.bank.account.bank.commun.exception.StatementNotFoundException;
import hexa.bank.account.bank.model.Statement;
import hexa.bank.account.bank.port.in.OperationHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/bank/operation")
public class OperationHistoryController {

    final OperationHistoryService operationHistoryService;

    public OperationHistoryController(OperationHistoryService operationHistoryService) {
        this.operationHistoryService = operationHistoryService;
    }

    @CrossOrigin
    @PostMapping(path = "/history")
    public ResponseEntity<List<Statement>> history(@RequestBody HistoryRequest historyRequest) throws StatementNotFoundException, InvalidArgumentException {
        var statement = operationHistoryService.history(historyRequest.getClientNumber(), historyRequest.getAccountNumber());
        return ResponseEntity.ok(statement);
    }
}
