package dev.Zerphyis.picpay.domain.interfaceCases;

import dev.Zerphyis.picpay.domain.entities.users.Users;

import java.math.BigDecimal;

public interface BalanceValidateInterface {
    void validate(Users payer, BigDecimal transferValue);
}
