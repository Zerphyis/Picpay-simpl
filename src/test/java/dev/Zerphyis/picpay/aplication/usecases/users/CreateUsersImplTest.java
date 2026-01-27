package dev.Zerphyis.picpay.aplication.usecases.users;

import dev.Zerphyis.picpay.aplication.dtos.CreateUserRequestDTO;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.entities.users.UsersType;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUsersImplTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private CreateUsersImpl createUsers;

    @Test
    void shouldCreateUserWithZeroBalanceWhenBalanceIsNull() {
        CreateUserRequestDTO dto = new CreateUserRequestDTO(
                "Otavio",
                "123",
                "email@test.com",
                "123",
                null,
                "COMMON"
        );

        when(userGateway.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Users user = createUsers.execute(dto);

        assertEquals(BigDecimal.ZERO, user.getBalance());
        assertEquals(UsersType.COMMON, user.getUserType());
        verify(userGateway).save(any(Users.class));
    }

    @Test
    void shouldThrowExceptionWhenUserTypeIsInvalid() {
        CreateUserRequestDTO dto = new CreateUserRequestDTO(
                "Otavio",
                "123",
                "email@test.com",
                "123",
                BigDecimal.TEN,
                "INVALID"
        );

        assertThrows(IllegalArgumentException.class, () -> createUsers.execute(dto));
    }

    @Test
    void shouldPropagateExceptionWhenRepositoryFails() {
        CreateUserRequestDTO dto = new CreateUserRequestDTO(
                "Otavio", "123", "email@test.com", "123",
                BigDecimal.TEN, "COMMON"
        );

        when(userGateway.save(any()))
                .thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class,
                () -> createUsers.execute(dto));
    }


}