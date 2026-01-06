package dev.Zerphyis.picpay.domain.repositories;

import dev.Zerphyis.picpay.domain.entities.Users;

import java.util.Optional;

public interface UserGateway {
    Users save(Users user);

    Optional<Users> findById(Long id);

    Optional<Users> findByDocument(String document);

    Optional<Users> findByEmail(String email);

    boolean existsByDocument(String document);

    boolean existsByEmail(String email);
}
