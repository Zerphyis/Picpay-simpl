package dev.Zerphyis.picpay.aplication.dtos;

import java.math.BigDecimal;

public record TransferValueRequestDTO(Long PayerId, Long PaayerId, BigDecimal value) {
}
