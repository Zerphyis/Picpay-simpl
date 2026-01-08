CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,

    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,

    value NUMERIC(19,2) NOT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_transaction_sender
        FOREIGN KEY (sender_id)
        REFERENCES users (id),

    CONSTRAINT fk_transaction_receiver
        FOREIGN KEY (receiver_id)
        REFERENCES users (id),

    CONSTRAINT chk_transaction_value_positive
        CHECK (value > 0)
);

CREATE INDEX idx_transaction_sender ON transactions(sender_id);
CREATE INDEX idx_transaction_receiver ON transactions(receiver_id);
