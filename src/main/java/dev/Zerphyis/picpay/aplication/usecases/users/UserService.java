package dev.Zerphyis.picpay.aplication.usecases.users;

import dev.Zerphyis.picpay.aplication.dtos.CreateUserRequestDTO;
import dev.Zerphyis.picpay.domain.entities.users.Users;

import java.util.List;

public class UserService {

        private final CreateUsersImpl createUserService;
        private final ListUserImpl listUsersService;

        public UserService(
                CreateUsersImpl createUserService,
                ListUserImpl listUsersService
        ) {
            this.createUserService = createUserService;
            this.listUsersService = listUsersService;
        }

        public Users create(CreateUserRequestDTO user) {
            return createUserService.execute(user);
        }

        public List<Users> listAll() {
            return listUsersService.execute();
        }
    }


