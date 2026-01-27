package dev.Zerphyis.picpay.aplication.usecases.users;

import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class ListUserImplTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private ListUserImpl listUser;

    @Test
    void shouldReturnAllUsers() {
        when(userGateway.findAll()).thenReturn(List.of(new Users()));

        List<Users> result = listUser.execute();

        assertFalse(result.isEmpty());
        verify(userGateway).findAll();
    }

    @Test
    void shouldThrowWhenRepositoryFails() {
        when(userGateway.findAll())
                .thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class,
                () -> listUser.execute());
    }

}
