package dev.Zerphyis.picpay.domain.entities.transfers;

import dev.Zerphyis.picpay.domain.entities.users.Users;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private Long id;
    private BigDecimal amount;
    private Users sender;
    private Users receiver;
    private TransactionStatus status;
    private final LocalDateTime createdAt;

    public Transaction(
            BigDecimal amount,
            Users sender,
            Users receiver
    ) {
        this(null, amount, sender, receiver, TransactionStatus.SUCCESS, LocalDateTime.now());
    }

    public Transaction(
            Long id,
            BigDecimal amount,
            Users sender,
            Users receiver,
            TransactionStatus status,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.amount = Objects.requireNonNull(amount);
        this.sender = Objects.requireNonNull(sender);
        this.receiver = Objects.requireNonNull(receiver);
        this.status = Objects.requireNonNull(status);
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public void markAsRefunded() {
        if (this.status == TransactionStatus.REFUNDED) {
            throw new IllegalStateException("Transação já estornada");
        }
        this.status = TransactionStatus.REFUNDED;
    }

    public boolean isRefunded() {
        return this.status == TransactionStatus.REFUNDED;
    }

    // getters
    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Users getSender() {
        return sender;
    }

    public Users getReceiver() {
        return receiver;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}