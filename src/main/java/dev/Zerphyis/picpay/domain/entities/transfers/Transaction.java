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
    private final LocalDateTime createdAt;

    public Transaction(
            BigDecimal amount,
            Users sender,
            Users receiver
    ) {
        this(null, amount, sender, receiver, LocalDateTime.now());
    }

    public Transaction(
            Long id,
            BigDecimal amount,
            Users sender,
            Users receiver,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.amount = Objects.requireNonNull(amount, "amount não pode ser nulo");
        this.sender = Objects.requireNonNull(sender, "sender não pode ser nulo");
        this.receiver = Objects.requireNonNull(receiver, "receiver não pode ser nulo");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt não pode ser nulo");
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = Objects.requireNonNull(amount, "amount não pode ser nulo");
    }

    public void setSender(Users sender) {
        this.sender = Objects.requireNonNull(sender, "sender não pode ser nulo");
    }

    public void setReceiver(Users receiver) {
        this.receiver = Objects.requireNonNull(receiver, "receiver não pode ser nulo");
    }
}
