package dev.Zerphyis.picpay.aplication.usecases;

import dev.Zerphyis.picpay.aplication.exceptions.InsufficientBalanceException;
import dev.Zerphyis.picpay.aplication.exceptions.MerchantTransferNotAllowedException;
import dev.Zerphyis.picpay.aplication.exceptions.UserNotFoundException;
import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.interfaceCases.TransferValueInterface;
import dev.Zerphyis.picpay.domain.repositories.TransactionGateway;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

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
            Long payerId,
            Long payeeId,
            BigDecimal value
    ) {
        Users payer = userGateway.findById(payerId)
                .orElseThrow(() -> new UserNotFoundException(payerId));

        Users payee = userGateway.findById(payeeId)
                .orElseThrow(() -> new UserNotFoundException(payeeId));

        if (payer.isMerchant()) {
            throw new MerchantTransferNotAllowedException();
        }

        if (payer.getBalance().compareTo(value) < 0) {
            throw new InsufficientBalanceException(
                    payer.getBalance(),
                    value
            );
        }

        payer.debit(value);
        payee.credit(value);

        userGateway.save(payer);
        userGateway.save(payee);

        Transaction transaction = new Transaction(
                payerId,
                payeeId,
                value
        );

        transactionGateway.save(transaction);
    }


}

