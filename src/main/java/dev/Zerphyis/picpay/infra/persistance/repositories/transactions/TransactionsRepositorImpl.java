package dev.Zerphyis.picpay.infra.persistance.repositories.transactions;

import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.repositories.TransactionGateway;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransactionsRepositorImpl implements TransactionGateway {

    private final TransactionsRepositoryJpa repositoryJpa;

    public TransactionsRepositorImpl(TransactionsRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return repositoryJpa.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return repositoryJpa.findById(id);
    }


    @Override
    public List<Transaction> findByIdUsers(Users receiver) {
        return repositoryJpa.findByIdUsers(receiver);
    }

    @Override
    public void delete(Transaction transaction) {
        repositoryJpa.delete(transaction);
    }
}
