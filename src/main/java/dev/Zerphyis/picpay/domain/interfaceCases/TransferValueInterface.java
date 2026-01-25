package dev.Zerphyis.picpay.domain.interfaceCases;

import dev.Zerphyis.picpay.domain.entities.users.Users;

import java.math.BigDecimal;

public interface TransferValueInterface {
    void execute(Users payerId, Users payeeId, BigDecimal value);
}
