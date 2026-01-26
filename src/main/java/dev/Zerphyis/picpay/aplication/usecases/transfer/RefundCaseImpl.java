package dev.Zerphyis.picpay.aplication.usecases.transfer;

import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.interfaceCases.RefundCaseInterface;
import dev.Zerphyis.picpay.domain.repositories.TransactionGateway;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;

import java.math.BigDecimal;

public class RefundCaseImpl implements RefundCaseInterface {
    private final TransactionGateway transactionGateway;
    private final UserGateway userGateway;

    public RefundCaseImpl(
            TransactionGateway transactionGateway,
            UserGateway userGateway
    ) {
        this.transactionGateway = transactionGateway;
        this.userGateway = userGateway;
    }
    @Override
    public void execute(Long transactionId) {

        Transaction originalTransaction = transactionGateway.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        Users sender = originalTransaction.getSender();

        Users receiver = originalTransaction.getReceiver();

        BigDecimal value = originalTransaction.getAmount();

        receiver.debit(value);
        sender.credit(value);

        userGateway.save(receiver);
        userGateway.save(sender);

        Transaction refund = new Transaction(
                value,
                receiver,
                sender
        );

        transactionGateway.save(refund);
    }
}
