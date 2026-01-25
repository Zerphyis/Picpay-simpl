package dev.Zerphyis.picpay.aplication.usecases.transfer;

import dev.Zerphyis.picpay.aplication.exceptions.InvalidTransferValueException;
import dev.Zerphyis.picpay.aplication.exceptions.SameUserTransferException;
import dev.Zerphyis.picpay.domain.interfaceCases.TransferValidationInterface;

import java.math.BigDecimal;

public class TransferValidationImpl implements TransferValidationInterface {
    @Override
    public void validate(Long payerId, Long payeeId, BigDecimal value) {

        if (payerId.equals(payeeId)) {
            throw new SameUserTransferException();
        }

        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransferValueException();
        }
    }
}
