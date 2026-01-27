package dev.Zerphyis.picpay.infra.persistance.repositories.transactions;


import dev.Zerphyis.picpay.infra.persistance.entites.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionsRepositoryJpa extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findBySenderId(Long senderId);
    @Query("""
        SELECT t
        FROM TransactionEntity t
        JOIN FETCH t.sender
        JOIN FETCH t.receiver
        WHERE t.id = :id
    """)
    Optional<TransactionEntity> findByIdWithUsers(@Param("id") Long id);
}
