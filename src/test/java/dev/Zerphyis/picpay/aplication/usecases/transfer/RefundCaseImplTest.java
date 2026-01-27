package dev.Zerphyis.picpay.aplication.usecases.transfer;

import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.entities.users.UsersType;
import dev.Zerphyis.picpay.domain.repositories.TransactionGateway;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RefundCaseImplTest {

    @Mock
    private TransactionGateway transactionGateway;

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private RefundCaseImpl refundCase;

    @Test
    void shouldRefundTransaction() {
        Users sender = new Users(UsersType.COMMON, BigDecimal.ZERO, "p","e","d","s");
        Users receiver = new Users(UsersType.COMMON, BigDecimal.TEN, "p","e","d","r");

        Transaction tx = spy(new Transaction(BigDecimal.ONE, sender, receiver));

        when(transactionGateway.findById(1L)).thenReturn(Optional.of(tx));

        refundCase.execute(1L);

        verify(transactionGateway, atLeastOnce()).save(any());
        verify(userGateway, times(2)).save(any());
    }

    @Test
    void shouldThrowWhenTransactionNotFound() {
        when(transactionGateway.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> refundCase.execute(1L));
    }

    @Test
    void shouldThrowWhenTransactionAlreadyRefunded() {
        Transaction tx = spy(new Transaction(
                BigDecimal.ONE,
                mock(Users.class),
                mock(Users.class)
        ));
        tx.markAsRefunded();

        when(transactionGateway.findById(1L))
                .thenReturn(Optional.of(tx));

        assertThrows(IllegalStateException.class,
                () -> refundCase.execute(1L));
    }

    @Test
    void shouldThrowWhenReceiverHasInsufficientBalance() {
        Users sender = new Users(UsersType.COMMON, BigDecimal.ZERO,"p","e","d","s");
        Users receiver = new Users(UsersType.COMMON, BigDecimal.ZERO,"p","e","d","r");

        Transaction tx = new Transaction(BigDecimal.TEN, sender, receiver);

        when(transactionGateway.findById(1L))
                .thenReturn(Optional.of(tx));

        assertThrows(IllegalStateException.class,
                () -> refundCase.execute(1L));
    }

}
