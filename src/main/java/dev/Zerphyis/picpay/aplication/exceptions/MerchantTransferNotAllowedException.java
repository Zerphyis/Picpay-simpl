package dev.Zerphyis.picpay.aplication.exceptions;

public class MerchantTransferNotAllowedException extends RuntimeException {
    public MerchantTransferNotAllowedException() {
        super("Usuários do tipo MERCHANT não podem iniciar transferências");
    }
}
