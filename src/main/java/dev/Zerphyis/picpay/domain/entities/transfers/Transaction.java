package dev.Zerphyis.picpay.domain.entities.transfers;

import dev.Zerphyis.picpay.domain.entities.users.Users;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private Long id;

    private Users reciverId;

    private Users senderId;

    private BigDecimal value;

    private LocalDateTime createdTime;

    public Transaction( BigDecimal value, Users senderId, Users reciverId) {
        this.value = value;
        this.senderId = senderId;
        this.reciverId = reciverId;
    }

    public Transaction() {
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Users getSenderId() {
        return senderId;
    }

    public void setSenderId(Users senderId) {
        this.senderId = senderId;
    }

    public Users getReciverId() {
        return reciverId;
    }

    public void setReciverId(Users reciverId) {
        this.reciverId = reciverId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
