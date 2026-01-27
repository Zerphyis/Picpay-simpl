package dev.Zerphyis.picpay.aplication.usecases.transfer;

import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.repositories.TransactionGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListAllTransactionImplTest {

    @Mock
    private TransactionGateway transactionGateway;

    @InjectMocks
    private ListAllTransactionImpl listAll;

    @Test
    void shouldReturnAllTransactions() {
        when(transactionGateway.findAll()).thenReturn(List.of(new Transaction(
                BigDecimal.ONE, mock(Users.class), mock(Users.class)
        )));

        List<Transaction> result = listAll.execute();

        assertFalse(result.isEmpty());
        verify(transactionGateway).findAll();
    }

    @Test
    void shouldThrowWhenRepositoryFails() {
        when(transactionGateway.findAll())
                .thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class,
                () -> listAll.execute());
    }

}
