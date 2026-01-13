package dev.Zerphyis.picpay.infra.persistance.mappers;

import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.infra.persistance.entites.TransactionEntity;

public class TransactionMapper {

    private TransactionMapper() {
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        return new TransactionEntity(
                transaction.getId(),
                transaction.getSenderId(),
                transaction.getReceiverId(),
                transaction.getValue(),
                transaction.getCreatedAt()
        );
    }

    public static Transaction toDomain(TransactionEntity entity) {
        return new Transaction(
                entity.getId(),
                entity.getSenderId(),
                entity.getReceiverId(),
                entity.getValue(),
                entity.getCreatedAt()
        );
    }
}
