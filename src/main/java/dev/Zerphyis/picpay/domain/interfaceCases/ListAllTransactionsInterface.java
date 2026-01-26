package dev.Zerphyis.picpay.domain.interfaceCases;

import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;

import java.util.List;

public interface ListAllTransactionsInterface {
    List<Transaction> execute();
}
