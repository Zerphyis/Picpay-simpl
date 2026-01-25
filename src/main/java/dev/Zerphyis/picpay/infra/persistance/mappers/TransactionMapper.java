package dev.Zerphyis.picpay.infra.persistance.mappers;

import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.infra.persistance.entites.TransactionEntity;
import dev.Zerphyis.picpay.infra.persistance.mappers.UsersMapper;

public class TransactionMapper {

    public static Transaction toDomain(TransactionEntity entity) {
        Users sender = UsersMapper.toDomain(entity.getSenderId());
        Users receiver = UsersMapper.toDomain(entity.getReceiverId());

        return new Transaction(
                entity.getId(),
                entity.getValue(),
                sender,
                receiver
        );
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();

        entity.setId(transaction.getId());
        entity.setValue(transaction.getAmount());
        entity.setSenderId(UsersMapper.toEntity(transaction.getSender()));
        entity.setReceiverId(UsersMapper.toEntity(transaction.getReceiver()));
        entity.setCreatedAt(transaction.getCreatedAt());

        return entity;
    }
}
