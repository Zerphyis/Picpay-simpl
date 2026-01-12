package dev.Zerphyis.picpay.domain.interfaceCases;

import java.math.BigDecimal;

public interface TransferValidationInterface {
      void validate(Long payerId, Long payeeId, BigDecimal value);
}
