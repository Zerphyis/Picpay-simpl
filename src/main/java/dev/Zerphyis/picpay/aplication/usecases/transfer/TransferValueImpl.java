package dev.Zerphyis.picpay.aplication.usecases.transfer;


import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.interfaceCases.TransferValueInterface;
import dev.Zerphyis.picpay.domain.repositories.TransactionGateway;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

public class TransferValueImpl implements TransferValueInterface {

    private final UserGateway userGateway;
    private final TransactionGateway transactionGateway;

    public TransferValueImpl(UserGateway userGateway, TransactionGateway transactionGateway) {
        this.userGateway = userGateway;
        this.transactionGateway = transactionGateway;
    }


    @Override
    @Transactional
    public void execute(
            Users payer,
            Users payee,
            BigDecimal value
    ) {
        Objects.requireNonNull(payer, "payer não pode ser nulo");
        Objects.requireNonNull(payee, "payee não pode ser nulo");
        Objects.requireNonNull(value, "value não pode ser nulo");

        payer.debit(value);
        payee.credit(value);

        userGateway.save(payer);
        userGateway.save(payee);

        Transaction transaction = new Transaction(
                value,
                payer,
                payee
        );

        transactionGateway.save(transaction);
    }

}

