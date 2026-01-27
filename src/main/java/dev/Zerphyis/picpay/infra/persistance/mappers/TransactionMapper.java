package dev.Zerphyis.picpay.infra.persistance.mappers;

import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.infra.persistance.entites.TransactionEntity;
import dev.Zerphyis.picpay.infra.persistance.entites.UsersEntity;

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
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        if (transaction == null) return null;

        TransactionEntity entity = new TransactionEntity();
        entity.setId(transaction.getId());
        entity.setValue(transaction.getAmount());
        entity.setStatus(transaction.getStatus());
        entity.setCreatedAt(transaction.getCreatedAt());

        UsersEntity senderRef = new UsersEntity();
        senderRef.setId(transaction.getSender().getId());

        UsersEntity receiverRef = new UsersEntity();
        receiverRef.setId(transaction.getReceiver().getId());

        entity.setSender(senderRef);
        entity.setReceiver(receiverRef);

        return entity;
    }
}
