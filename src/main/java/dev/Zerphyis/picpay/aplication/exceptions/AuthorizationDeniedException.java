package dev.Zerphyis.picpay.aplication.exceptions;

public class AuthorizationDeniedException extends RuntimeException {

    public AuthorizationDeniedException() {
        super("Transação não autorizada pelo serviço externo");
    }
}
