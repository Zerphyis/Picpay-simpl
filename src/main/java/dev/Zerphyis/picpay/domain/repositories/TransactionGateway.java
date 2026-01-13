package dev.Zerphyis.picpay.domain.repositories;

import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionGateway {

    Transaction save(Transaction transaction);

    Optional<Transaction> findById(Long id);

    List<Transaction> findBySenderId(Long senderId);

    void deleteById(Long id);
}
