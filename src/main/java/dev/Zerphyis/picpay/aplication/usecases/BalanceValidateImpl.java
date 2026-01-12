package dev.Zerphyis.picpay.aplication.usecases;

import dev.Zerphyis.picpay.aplication.exceptions.InsufficientBalanceException;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.interfaceCases.BalanceValidateInterface;

import java.math.BigDecimal;

public class BalanceValidateImpl implements BalanceValidateInterface {

    @Override
    public void validate(Users payer, BigDecimal transferValue) {
        BigDecimal currentBalance = payer.getBalance();
        if (currentBalance == null) {
            throw new InsufficientBalanceException(BigDecimal.ZERO, transferValue);
        }

        if (currentBalance.compareTo(transferValue) < 0) {
            throw new InsufficientBalanceException(currentBalance, transferValue);
        }
    }
}
