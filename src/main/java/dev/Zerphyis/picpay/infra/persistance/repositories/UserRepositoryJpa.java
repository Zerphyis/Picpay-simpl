package dev.Zerphyis.picpay.infra.persistance.repositories;

import dev.Zerphyis.picpay.infra.persistance.entites.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<UsersEntity,Long> {
    Optional<UsersEntity> findByDocument(String document);

    Optional<UsersEntity> findByEmail(String email);

    boolean existsByDocument(String document);

    boolean existsByEmail(String email);
}
