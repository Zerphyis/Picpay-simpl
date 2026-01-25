package dev.Zerphyis.picpay.aplication.usecases.transfer;

import dev.Zerphyis.picpay.aplication.exceptions.UserNotFoundException;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.interfaceCases.VerifyUserInterface;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;

public class VerifyUsersImpl implements VerifyUserInterface {

    private final UserGateway userGateway;

    public VerifyUsersImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public Users getExistingUser(Long userId) {
        return userGateway.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
