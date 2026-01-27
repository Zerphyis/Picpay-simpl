package dev.Zerphyis.picpay.aplication.usecases.transfer;

import dev.Zerphyis.picpay.aplication.exceptions.InvalidTransferValueException;
import dev.Zerphyis.picpay.aplication.exceptions.SameUserTransferException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransferValidationImplTest {

    private final TransferValidationImpl validation = new TransferValidationImpl();

    @Test
    void shouldThrowWhenSameUser() {
        assertThrows(SameUserTransferException.class,
                () -> validation.validate(1L, 1L, BigDecimal.TEN));
    }

    @Test
    void shouldThrowWhenValueIsZero() {
        assertThrows(InvalidTransferValueException.class,
                () -> validation.validate(1L, 2L, BigDecimal.ZERO));
    }

    @Test
    void shouldPassWhenValid() {
        assertDoesNotThrow(() ->
                validation.validate(1L, 2L, BigDecimal.ONE));
    }

    @Test
    void shouldThrowWhenValueIsNull() {
        assertThrows(NullPointerException.class,
                () -> validation.validate(1L, 2L, null));
    }
}
