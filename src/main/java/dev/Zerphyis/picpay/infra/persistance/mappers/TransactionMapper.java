package dev.Zerphyis.picpay.infra.persistance.mappers;

import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.domain.entities.users.Users;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionMapper {
    public static Transaction toEntity(
            BigDecimal value,
            Users sender,
            Users receiver
    ) {
        return new Transaction(
                value,
                sender,
                receiver
        );
    }

    public static Transaction toEntity(
            Long id,
            BigDecimal value,
            Users sender,
            Users receiver,
            LocalDateTime createdTime
    ) {
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setValue(value);
        transaction.setSenderId(sender);
        transaction.setReciverId(receiver);
        transaction.setCreatedTime(createdTime);
        return transaction;
    }
}
