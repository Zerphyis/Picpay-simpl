package dev.Zerphyis.picpay.domain.interfaceCases;

import java.math.BigDecimal;

public interface TransferValueInterface {
    void execute(Long payerId, Long payeeId, BigDecimal value);
}
