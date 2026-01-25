package dev.Zerphyis.picpay.domain.entities.users;

import java.math.BigDecimal;
import java.util.Objects;

public class Users {

    private Long id;
    private String fullname;
    private String document;
    private String email;
    private String password;
    private BigDecimal balance;
    private UsersType usersType;

    public Users(
            Long id,
            UsersType usersType,
            BigDecimal balance,
            String password,
            String email,
            String document,
            String fullname
    ) {
        this.id = id;
        this.usersType = usersType;
        this.balance = balance;
        this.password = password;
        this.email = email;
        this.document = document;
        this.fullname = fullname;
    }

    public Users(
            UsersType usersType,
            BigDecimal balance,
            String password,
            String email,
            String document,
            String fullname
    ) {
        this(null, usersType, balance, password, email, document, fullname);
    }

    public Users() {
    }

    public Long getId() {
        return id;
    }


    public void debit(BigDecimal value) {
        validateValue(value);

        if (this.balance.compareTo(value) < 0) {
            throw new IllegalStateException("Saldo insuficiente");
        }

        this.balance = this.balance.subtract(value);
    }

    public void credit(BigDecimal value) {
        validateValue(value);
        this.balance = this.balance.add(value);
    }

    public boolean isMerchant() {
        return UsersType.MERCHANT.equals(this.usersType);
    }

    private void validateValue(BigDecimal value) {
        if (value == null || value.signum() <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
    }

    /* ================= GETTERS ================= */

    public UsersType getUserType() {
        return usersType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }

    public String getFullname() {
        return fullname;
    }

    /* ================= EQUALS & HASHCODE ================= */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
