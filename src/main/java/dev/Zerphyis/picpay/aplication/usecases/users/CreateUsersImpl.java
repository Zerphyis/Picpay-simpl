package dev.Zerphyis.picpay.aplication.usecases.users;

import dev.Zerphyis.picpay.aplication.dtos.CreateUserRequestDTO;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.entities.users.UsersType;
import dev.Zerphyis.picpay.domain.interfaceCases.CreateUserInterface;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;

import java.math.BigDecimal;

public class CreateUsersImpl implements CreateUserInterface {

    private final UserGateway repository;

    public CreateUsersImpl(UserGateway repository) {
        this.repository = repository;
    }

    @Override
    public Users execute(CreateUserRequestDTO dto) {

        UsersType userType = UsersType.fromString(dto.userType());

        BigDecimal initialBalance =
                dto.balance() != null ? dto.balance() : BigDecimal.ZERO;

        Users user = new Users(
                userType,
                initialBalance,
                dto.password(),
                dto.email(),
                dto.document(),
                dto.fullname()
        );

        return repository.save(user);
    }
}
