package dev.Zerphyis.picpay.aplication.exceptions;

public class InvalidTransferValueException extends RuntimeException {
    public InvalidTransferValueException() {

        super("O valor da transferência deve ser maior que zero");
    }
}
