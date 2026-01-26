package dev.Zerphyis.picpay.infra.persistance.mappers;

import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.infra.persistance.entites.TransactionEntity;

public class TransactionMapper {

    public static Transaction toDomain(TransactionEntity entity) {
        if (entity == null) return null;

        Users sender = UsersMapper.toDomain(entity.getSender());
        Users receiver = UsersMapper.toDomain(entity.getReceiver());

        return new Transaction(
                entity.getId(),
                entity.getValue(),
                sender,
                receiver,
                entity.getCreatedAt()
        );
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        if (transaction == null) return null;

        TransactionEntity entity = new TransactionEntity();
        entity.setId(transaction.getId());
        entity.setValue(transaction.getAmount());
        entity.setSender(UsersMapper.toEntity(transaction.getSender()));
        entity.setReceiver(UsersMapper.toEntity(transaction.getReceiver()));
        entity.setCreatedAt(transaction.getCreatedAt());

        return entity;
    }
}
