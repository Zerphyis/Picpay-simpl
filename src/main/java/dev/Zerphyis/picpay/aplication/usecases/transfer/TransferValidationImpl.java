package dev.Zerphyis.picpay.aplication.usecases.transfer;

import dev.Zerphyis.picpay.aplication.exceptions.InvalidTransferValueException;
import dev.Zerphyis.picpay.aplication.exceptions.SameUserTransferException;
import dev.Zerphyis.picpay.domain.interfaceCases.TransferValidationInterface;

import java.math.BigDecimal;

public class TransferValidationImpl implements TransferValidationInterface {
    @Override
    public void validate(Long payerId, Long payeeId, BigDecimal value) {
        if (payerId == null) {
            throw new IllegalArgumentException("PayerId não pode ser nulo");
        }

        if (payeeId == null) {
            throw new IllegalArgumentException("PayeeId não pode ser nulo");
        }

        if (payerId.equals(payeeId)) {
            throw new SameUserTransferException();
        }

        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransferValueException();
        }
    }
}
