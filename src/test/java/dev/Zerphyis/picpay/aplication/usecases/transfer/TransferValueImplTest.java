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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferValueImplTest {

    @Mock
    private UserGateway userGateway;

    @Mock
    private TransactionGateway transactionGateway;

    @InjectMocks
    private TransferValueImpl transferValue;

    @Test
    void shouldTransferValueSuccessfully() {
        Users payer = new Users(UsersType.COMMON, BigDecimal.TEN, "p", "e", "d", "payer");
        Users payee = new Users(UsersType.COMMON, BigDecimal.ZERO, "p", "e", "d", "payee");

        transferValue.execute(payer, payee, BigDecimal.ONE);

        verify(userGateway, times(2)).save(any(Users.class));
        verify(transactionGateway).save(any(Transaction.class));
    }
    @Test
    void shouldThrowWhenPayerIsNull() {
        assertThrows(NullPointerException.class,
                () -> transferValue.execute(null, mock(Users.class), BigDecimal.ONE));
    }

    @Test
    void shouldThrowWhenPayeeIsNull() {
        assertThrows(NullPointerException.class,
                () -> transferValue.execute(mock(Users.class), null, BigDecimal.ONE));
    }

    @Test
    void shouldThrowWhenValueIsNull() {
        assertThrows(NullPointerException.class,
                () -> transferValue.execute(mock(Users.class), mock(Users.class), null));
    }

    @Test
    void shouldThrowWhenBalanceIsInsufficient() {
        Users payer = new Users(
                UsersType.COMMON, BigDecimal.ZERO, "p","e","d","payer"
        );
        Users payee = new Users(
                UsersType.COMMON, BigDecimal.ZERO, "p","e","d","payee"
        );

        assertThrows(IllegalStateException.class,
                () -> transferValue.execute(payer, payee, BigDecimal.ONE));
    }



}
