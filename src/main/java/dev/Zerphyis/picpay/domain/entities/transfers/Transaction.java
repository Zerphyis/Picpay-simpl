package dev.Zerphyis.picpay.domain.entities.transfers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private final Long id;
    private final Long senderId;
    private final Long receiverId;
    private final BigDecimal value;
    private final LocalDateTime createdAt;


    public Transaction(Long senderId, Long receiverId, BigDecimal value) {
        this(null, senderId, receiverId, value, LocalDateTime.now());
    }


    public Transaction(
            Long id,
            Long senderId,
            Long receiverId,
            BigDecimal value,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.senderId = Objects.requireNonNull(senderId, "senderId não pode ser nulo");
        this.receiverId = Objects.requireNonNull(receiverId, "receiverId não pode ser nulo");
        this.value = Objects.requireNonNull(value, "value não pode ser nulo");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt não pode ser nulo");

        if (value.signum() <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser maior que zero");
        }
    }


    public Long getId() {
        return id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
