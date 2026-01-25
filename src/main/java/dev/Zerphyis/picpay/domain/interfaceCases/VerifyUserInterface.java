package dev.Zerphyis.picpay.domain.interfaceCases;

import dev.Zerphyis.picpay.domain.entities.users.Users;

public interface VerifyUserInterface {
    Users getExistingUser(Long userId);
}
