package dev.Zerphyis.picpay.aplication.dtos;

import java.math.BigDecimal;

public record TransferValueRequestDTO(Long payerId, Long payeeId, BigDecimal value) {
}
