package dev.Zerphyis.picpay.infra.persistance.repositories.transactions;

import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.domain.repositories.TransactionGateway;
import dev.Zerphyis.picpay.infra.persistance.entites.TransactionEntity;
import dev.Zerphyis.picpay.infra.persistance.mappers.TransactionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransactionsRepositoryImpl implements TransactionGateway {

    private final TransactionsRepositoryJpa repositoryJpa;

    public TransactionsRepositoryImpl(TransactionsRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }


    @Override
    public void save(Transaction transaction) {
        TransactionEntity entity = TransactionMapper.toEntity(transaction);
        repositoryJpa.save(entity);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return repositoryJpa.findByIdWithUsers(id)
                .map(TransactionMapper::toDomain);
    }

    @Override
    public List<Transaction> findAll() {
        return repositoryJpa.findAll()
                .stream()
                .map(TransactionMapper::toDomain)
                .toList();
    }

    @Override
    public List<Transaction> findBySenderId(Long senderId) {
        return repositoryJpa.findBySenderId(senderId)
                .stream()
                .map(TransactionMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repositoryJpa.deleteById(id);
    }
}
