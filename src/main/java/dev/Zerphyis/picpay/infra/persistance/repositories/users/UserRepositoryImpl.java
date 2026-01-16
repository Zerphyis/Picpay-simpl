package dev.Zerphyis.picpay.infra.persistance.repositories.users;

import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;
import dev.Zerphyis.picpay.infra.persistance.entites.UsersEntity;
import dev.Zerphyis.picpay.infra.persistance.mappers.UsersMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class UserRepositoryImpl implements UserGateway {
    private final UserRepositoryJpa springRepository;

    public UserRepositoryImpl(UserRepositoryJpa springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Users save(Users user) {
        UsersEntity entity = UsersMapper.toEntity(user);
        UsersEntity saved = springRepository.save(entity);
        return UsersMapper.toDomain(saved);
    }

    @Override
    public Optional<Users> findById(Long id) {
        return springRepository
                .findById(id)
                .map(UsersMapper::toDomain);
    }

    @Override
    public List<Users> findAll() {
        return springRepository
                .findAll()
                .stream()
                .map(UsersMapper::toDomain)
                .toList();
    }


    @Override
    public Optional<Users> findByDocument(String document) {
        return springRepository
                .findByDocument(document)
                .map(UsersMapper::toDomain);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return springRepository
                .findByEmail(email)
                .map(UsersMapper::toDomain);
    }

    @Override
    public boolean existsByDocument(String document) {
        return springRepository.existsByDocument(document);
    }

    @Override
    public boolean existsByEmail(String email) {
        return springRepository.existsByEmail(email);
    }
}
