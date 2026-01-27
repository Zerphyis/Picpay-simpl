CREATE TABLE transactions
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    sender_id   BIGINT         NOT NULL,
    receiver_id BIGINT         NOT NULL,

    value       DECIMAL(19, 2) NOT NULL,
    created_at  TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_transaction_sender
        FOREIGN KEY (sender_id)
            REFERENCES users (id),

    CONSTRAINT fk_transaction_receiver
        FOREIGN KEY (receiver_id)
            REFERENCES users (id),

    CONSTRAINT chk_transaction_value_positive
        CHECK (value > 0)
) ENGINE=InnoDB;
