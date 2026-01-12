package dev.Zerphyis.picpay.domain.interfaceCases;

import dev.Zerphyis.picpay.aplication.dtos.CreateUserRequestDTO;
import dev.Zerphyis.picpay.domain.entities.users.Users;

public interface CreateUserInterface {
    public Users execute(CreateUserRequestDTO dto);

}
