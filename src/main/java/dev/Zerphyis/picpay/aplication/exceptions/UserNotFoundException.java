package dev.Zerphyis.picpay.aplication.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("Usuário não encontrado para o ID: " + userId);
    }
}
