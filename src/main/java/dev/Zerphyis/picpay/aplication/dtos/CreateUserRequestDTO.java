package dev.Zerphyis.picpay.aplication.dtos;

public record CreateUserRequestDTO(
        String fullName,
        String document,
        String email,
        String password,
        String userType
) {
}
