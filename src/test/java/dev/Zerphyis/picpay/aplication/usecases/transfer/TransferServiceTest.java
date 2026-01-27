package dev.Zerphyis.picpay.aplication.usecases.transfer;

import dev.Zerphyis.picpay.aplication.exceptions.SameUserTransferException;
import dev.Zerphyis.picpay.aplication.httpsmocks.AuthorizationService;
import dev.Zerphyis.picpay.aplication.usecases.BalanceValidateImpl;
import dev.Zerphyis.picpay.aplication.usecases.NotifyTransferResult;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock VerifyUsersImpl verifyUsers;
    @Mock TransferValidationImpl transferValidation;
    @Mock ValidateMerchantUserImpl merchantValidation;
    @Mock
    BalanceValidateImpl balanceValidation;
    @Mock
    AuthorizationService authorizationService;
    @Mock TransferValueImpl transferValue;
    @Mock
    NotifyTransferResult notify;
    @Mock RefundCaseImpl refundCase;
    @Mock ListAllTransactionImpl listAll;

    @InjectMocks
    TransferService service;

    @Test
    void shouldExecuteTransferFlow() {
        Users payer = mock(Users.class);
        Users payee = mock(Users.class);

        when(verifyUsers.getExistingUser(1L)).thenReturn(payer);
        when(verifyUsers.getExistingUser(2L)).thenReturn(payee);

        service.execute(1L, 2L, BigDecimal.ONE);

        verify(authorizationService).authorize();
        verify(transferValue).execute(payer, payee, BigDecimal.ONE);
        verify(notify).execute(any(), any());
    }

    @Test
    void shouldStopFlowWhenValidationFails() {
        doThrow(new SameUserTransferException())
                .when(transferValidation)
                .validate(any(), any(), any());

        assertThrows(SameUserTransferException.class,
                () -> service.execute(1L, 1L, BigDecimal.ONE));

        verifyNoInteractions(verifyUsers, transferValue, notify);
    }

}
