package dev.Zerphyis.picpay.infra.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.Zerphyis.picpay.aplication.dtos.TransferValueRequestDTO;
import dev.Zerphyis.picpay.aplication.exceptions.*;
import dev.Zerphyis.picpay.aplication.usecases.transfer.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransferController.class)
@Import(HandleController.class)
class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransferService transferService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldTransferSuccessfully() throws Exception {
        TransferValueRequestDTO dto =
                new TransferValueRequestDTO(1L, 2L, BigDecimal.TEN);

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        verify(transferService)
                .execute(1L, 2L, BigDecimal.TEN);
    }

    @Test
    void shouldReturn404WhenUserNotFound() throws Exception {
        doThrow(new UserNotFoundException(1L))
                .when(transferService)
                .execute(any(), any(), any());

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"payerId\":1,\"payeeId\":2,\"value\":10}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void shouldReturn403WhenMerchantTransfer() throws Exception {
        doThrow(new MerchantTransferNotAllowedException())
                .when(transferService)
                .execute(any(), any(), any());

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"payerId\":1,\"payeeId\":2,\"value\":10}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldRefundSuccessfully() throws Exception {
        mockMvc.perform(post("/api/transfers/10/refund"))
                .andExpect(status().isNoContent());

        verify(transferService).refund(10L);
    }

    @Test
    void shouldListAllTransactionsSuccessfully() throws Exception {
        when(transferService.listAllTransactions())
                .thenReturn(java.util.List.of());

        mockMvc.perform(get("/api/transfers"))
                .andExpect(status().isOk());
    }
}
