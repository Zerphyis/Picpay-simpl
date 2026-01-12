package dev.Zerphyis.picpay.aplication.exceptions;

import java.math.BigDecimal;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(BigDecimal balance, BigDecimal value) {
        super(
                "Saldo insuficiente. Saldo atual: " + balance +
                        " | Valor da transferência: " + value
        );
    }
}
