package dev.Zerphyis.picpay.aplication.usecases.transfer;

import dev.Zerphyis.picpay.aplication.exceptions.UserNotFoundException;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VerifyUsersImplTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private VerifyUsersImpl verifyUsers;

    @Test
    void shouldReturnUserWhenExists() {
        Users user = new Users();
        when(userGateway.findById(1L)).thenReturn(Optional.of(user));

        Users result = verifyUsers.getExistingUser(1L);

        assertEquals(user, result);
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        when(userGateway.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> verifyUsers.getExistingUser(1L));
    }
}
