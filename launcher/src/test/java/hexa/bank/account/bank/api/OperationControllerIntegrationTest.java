package hexa.bank.account.bank.api;

import hexa.bank.account.bank.BankApplication;
import hexa.bank.account.bank.api.request.OperationRequest;
import hexa.bank.account.bank.model.Operation;
import hexa.bank.account.bank.model.Statement;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = BankApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OperationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void shouldCallApiDepositAndMakeADeposit() {
        var request = new OperationRequest(444444, 5555555, 100D);
        var path = "http://localhost:" + port + "/api/v1/bank/operation" + "/deposit";
        ResponseEntity<Statement> responseEntity = this.restTemplate.postForEntity(path, request, Statement.class);

        assertStatement(responseEntity, request, Operation.DEPOSIT, 100.0, 5764.0);
    }

    @Test
    @Order(2)
    void shouldCallApiWithdrawalAndMakeAWithdrawal() {
        var request = new OperationRequest(444444, 5555555, 100D);
        var path = "http://localhost:" + port + "/api/v1/bank/operation" + "/withdrawal";
        ResponseEntity<Statement> responseEntity = this.restTemplate.postForEntity(path, request, Statement.class);

        assertStatement(responseEntity, request, Operation.WITHDRAWAL, 100.0, 5664.0);
    }

    @Test
    @Order(3)
    void shouldCallApiHistory() {
        var request = new OperationRequest(444444, 5555555, 100D);
        var path = "http://localhost:" + port + "/api/v1/bank/operation" + "/history";
        ResponseEntity<Statement[]> responseEntity = this.restTemplate.postForEntity(path, request, Statement[].class);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        var statements = responseEntity.getBody();
        assertThat(statements).hasSize(2);
        assertThat(statements).extracting("operation").containsExactly(Operation.WITHDRAWAL, Operation.DEPOSIT);
    }

    private void assertStatement(ResponseEntity<Statement> responseEntity, OperationRequest request, Operation deposit, double amount, double balance) {
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getAmount()).isEqualTo(request.getAmount());
        assertThat(responseEntity.getBody().getOperation()).isEqualTo(deposit);
        assertThat(responseEntity.getBody().getAmount()).isEqualTo(amount);
        assertThat(responseEntity.getBody().getBalance()).isEqualTo(balance);
    }
}