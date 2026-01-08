package dev.Zerphyis.picpay.infra.persistance.repositories.transactions;

import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepositoryJpa extends JpaRepository<Transaction,Long> {
    List<Transaction> findByIdUsers(Users sender);
}
