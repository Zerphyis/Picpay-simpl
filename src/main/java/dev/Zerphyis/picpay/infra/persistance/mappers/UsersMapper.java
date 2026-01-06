package dev.Zerphyis.picpay.infra.persistance.mappers;

import dev.Zerphyis.picpay.domain.entities.Users;
import dev.Zerphyis.picpay.infra.persistance.UsersEntity;

public class UsersMapper {

    public static UsersEntity toEntity(Users user) {
        if (user == null) return null;
        return new UsersEntity(
                user.getId(),
                user.getFullname(),
                user.getDocument(),
                user.getEmail(),
                user.getPassword(),
                user.getBalance(),
                user.getUserType()
        );
    }

    public static Users toDomain(UsersEntity entity) {
        if (entity == null) return null;
        Users user = new Users(
                entity.getUsersType(),
                entity.getBalance(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getDocument(),
                entity.getFullname()
        );
        return user;
    }
}
