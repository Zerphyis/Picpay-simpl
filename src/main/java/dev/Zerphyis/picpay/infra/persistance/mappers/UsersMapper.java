package dev.Zerphyis.picpay.infra.persistance.mappers;

import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.infra.persistance.entites.UsersEntity;

public class UsersMapper {

    public static UsersEntity toEntity(Users user) {
        if (user == null) return null;

        UsersEntity entity = new UsersEntity(
                user.getFullname(),
                user.getDocument(),
                user.getEmail(),
                user.getPassword(),
                user.getBalance(),
                user.getUserType()
        );


        entity.setId(user.getId());

        return entity;
    }

    public static Users toDomain(UsersEntity entity) {
        if (entity == null) return null;

        return new Users(
                entity.getId(),
                entity.getUsersType(),
                entity.getBalance(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getDocument(),
                entity.getFullname()
        );
    }
}
