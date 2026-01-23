package dev.Zerphyis.picpay.aplication.usecases.users;

import dev.Zerphyis.picpay.aplication.dtos.CreateUserRequestDTO;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.entities.users.UsersType;
import dev.Zerphyis.picpay.domain.interfaceCases.CreateUserInterface;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;

public class CreateUsersImpl implements CreateUserInterface {

    private final UserGateway repository;

    public CreateUsersImpl(UserGateway repository) {
        this.repository = repository;
    }



    @Override
    public Users execute(CreateUserRequestDTO dto) {
        UsersType userType = UsersType.fromString(dto.userType());

        Users user = new Users();
        user.setFullname(dto.fullname());
        user.setBalance(dto.balance());
        user.setDocument(dto.document());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setUserType(userType);


        return repository.save(user);
    }
}
