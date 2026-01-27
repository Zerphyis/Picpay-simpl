package dev.Zerphyis.picpay.aplication.usecases;

import dev.Zerphyis.picpay.aplication.exceptions.InsufficientBalanceException;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BalanceValidateImplTest {

    private final BalanceValidateImpl validator = new BalanceValidateImpl();

    @Test
    void shouldThrowWhenBalanceIsNull() {
        Users user = mock(Users.class);
        when(user.getBalance()).thenReturn(null);

        assertThrows(InsufficientBalanceException.class,
                () -> validator.validate(user, BigDecimal.TEN));
    }

    @Test
    void shouldThrowWhenBalanceIsInsufficient() {
        Users user = mock(Users.class);
        when(user.getBalance()).thenReturn(BigDecimal.ONE);

        assertThrows(InsufficientBalanceException.class,
                () -> validator.validate(user, BigDecimal.TEN));
    }

    @Test
    void shouldPassWhenBalanceIsEnough() {
        Users user = mock(Users.class);
        when(user.getBalance()).thenReturn(BigDecimal.TEN);

        assertDoesNotThrow(() ->
                validator.validate(user, BigDecimal.ONE));
    }

    @Test
    void shouldThrowWhenPayerIsNull() {
        assertThrows(NullPointerException.class,
                () -> validator.validate(null, BigDecimal.TEN));
    }

}
