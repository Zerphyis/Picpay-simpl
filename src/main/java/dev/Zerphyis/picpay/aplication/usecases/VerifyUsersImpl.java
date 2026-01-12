package dev.Zerphyis.picpay.aplication.usecases;

import dev.Zerphyis.picpay.aplication.exceptions.UserNotFoundException;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.interfaceCases.VerifyUserInterface;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;

public class VerifyUsersImpl implements VerifyUserInterface {

    private final UserGateway repository;

    public VerifyUsersImpl(UserGateway repository) {
        this.repository = repository;
    }

    public Users validateAndGet(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }


    @Override
    public Long execute(Long id) {
        validateAndGet(id);
        return id;
    }
}
