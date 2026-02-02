package dev.Zerphyis.picpay.infra.controllers;

import dev.Zerphyis.picpay.domain.entities.transfers.TransactionStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TransactionsStatusConverter  implements AttributeConverter<TransactionStatus, String> {

    @Override
    public String convertToDatabaseColumn(TransactionStatus status) {
        return status == null ? null : status.name();
    }

    @Override
    public TransactionStatus convertToEntityAttribute(String value) {
        return value == null ? null : TransactionStatus.valueOf(value);
    }


    }

