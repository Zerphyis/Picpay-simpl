package dev.Zerphyis.picpay.domain.entities.users;

public enum UsersType {
    COMMON,
    MERCHANT;


    public static UsersType fromString(String value) {
        return UsersType.valueOf(value.toUpperCase());
    }
}
