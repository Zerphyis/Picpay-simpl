package dev.Zerphyis.picpay.aplication.usecases.transfer;

import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.domain.interfaceCases.ListAllTransactionsInterface;
import dev.Zerphyis.picpay.domain.repositories.TransactionGateway;

import java.util.List;

public class ListAllTransactionImpl implements ListAllTransactionsInterface {
    private final TransactionGateway transactionGateway;

    public ListAllTransactionImpl(TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    @Override
    public List<Transaction> execute() {
        return transactionGateway.findAll();
    }
}
