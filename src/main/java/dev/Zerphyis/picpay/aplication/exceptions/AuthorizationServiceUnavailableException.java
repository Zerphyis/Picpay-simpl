package dev.Zerphyis.picpay.aplication.exceptions;

public class AuthorizationServiceUnavailableException extends RuntimeException {
    public AuthorizationServiceUnavailableException() {
        super("Serviço de autorização indisponível");
    }
}
