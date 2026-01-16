package dev.Zerphyis.picpay.aplication.usecases;

public class NotifyTransferResult {
    public void execute(Long payerId, Long payeeId) {
        System.out.println(
                "Transferência realizada com sucesso: " +
                        payerId + " -> " + payeeId
        );
    }
}
