package dev.Zerphyis.picpay.aplication.exceptions;

public class SameUserTransferException extends RuntimeException {
    public SameUserTransferException() {
        super("Payer e Payee não podem ser o mesmo usuário");
    }
}
