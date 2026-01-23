package dev.Zerphyis.picpay.aplication.usecases.users;

import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.interfaceCases.ListUserInterface;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;

import java.util.List;

public class ListUserImpl implements ListUserInterface {

    private final UserGateway userGateway;

    public ListUserImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<Users> execute() {
        return userGateway.findAll();
    }
}
