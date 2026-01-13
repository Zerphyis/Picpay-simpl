package dev.Zerphyis.picpay.infra.persistance.repositories.transactions;


import dev.Zerphyis.picpay.infra.persistance.entites.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepositoryJpa extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findBySenderId(Long senderId);
}
