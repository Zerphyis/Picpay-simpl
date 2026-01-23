package dev.Zerphyis.picpay.aplication.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record CreateUserRequestDTO(
        String fullname,

        String document,
        String email,
        String password,
        BigDecimal balance,
        String userType
) {
}
