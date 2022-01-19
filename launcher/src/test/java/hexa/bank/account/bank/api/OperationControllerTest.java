package hexa.bank.account.bank.api;

import hexa.bank.account.bank.model.Account;
import hexa.bank.account.bank.model.Operation;
import hexa.bank.account.bank.model.Statement;
import hexa.bank.account.bank.port.in.OperationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OperationController.class)
class OperationControllerTest {

    @MockBean
    private OperationService operationService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void deposit() throws Exception {
        var statement = new Statement(Operation.DEPOSIT, LocalDate.now(), 100D, 200D, new Account());

        when(operationService.deposit(eq(1111), eq(2222), eq(100D))).thenReturn(statement);

        var result = mockMvc.perform(post("/api/v1/bank/operation/deposit", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"clientNumber\": 1111,\n" +
                                "    \"accountNumber\": 2222,\n" +
                                "    \"amount\":100\n" +
                                "}"))
                .andExpect(status().isOk());

        Assertions.assertEquals("{\"operation\":\"DEPOSIT\",\"date\":\"" + LocalDate.now() + "\",\"amount\":100.0,\"balance\":200.0}",
                result.andReturn().getResponse().getContentAsString());
        verify(operationService).deposit(eq(1111), eq(2222), eq(100D));
    }

}