CREATE TABLE users
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,

    fullname  VARCHAR(255)   NOT NULL,

    document  VARCHAR(14)    NOT NULL,
    email     VARCHAR(255)   NOT NULL,

    password  VARCHAR(255)   NOT NULL,
    balance   DECIMAL(19, 2) NOT NULL DEFAULT 0.00,
    user_type VARCHAR(20)    NOT NULL,

    CONSTRAINT uk_users_document UNIQUE (document),
    CONSTRAINT uk_users_email UNIQUE (email)
) ENGINE=InnoDB;
