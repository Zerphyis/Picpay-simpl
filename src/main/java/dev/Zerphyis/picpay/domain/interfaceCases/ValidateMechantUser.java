package dev.Zerphyis.picpay.domain.interfaceCases;

import dev.Zerphyis.picpay.domain.entities.users.Users;

public interface ValidateMechantUser {
    void validate(Users payer);
}
