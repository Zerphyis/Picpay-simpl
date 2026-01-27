package dev.Zerphyis.picpay.aplication.usecases.users;

import dev.Zerphyis.picpay.aplication.dtos.CreateUserRequestDTO;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.entities.users.UsersType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private CreateUsersImpl createUserService;

    @Mock
    private ListUserImpl listUsersService;

    @InjectMocks
    private UserService userService;

    private Users user;
    private CreateUserRequestDTO requestDTO;

    @BeforeEach
    void setup() {
        user = new Users(
                UsersType.COMMON,
                BigDecimal.valueOf(100.00),
                "123456",
                "teste@email.com",
                "12345678900",
                "Usuário Teste"
        );

        requestDTO = new CreateUserRequestDTO(
                "Usuário Teste",
                "12345678900",
                "teste@email.com",
                "123456",
                BigDecimal.valueOf(100.00),
                "COMMON"
        );
    }


    @Test
    void shouldCreateUserSuccessfully() {
        when(createUserService.execute(requestDTO)).thenReturn(user);

        Users result = userService.create(requestDTO);

        assertNotNull(result);
        assertEquals("Usuário Teste", result.getFullname());

        verify(createUserService).execute(requestDTO);
        verifyNoInteractions(listUsersService);
    }


    @Test
    void shouldThrowExceptionWhenCreateUserFails() {
        RuntimeException exception = new RuntimeException("Erro ao criar usuário");

        when(createUserService.execute(requestDTO)).thenThrow(exception);

        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> userService.create(requestDTO)
        );

        assertEquals("Erro ao criar usuário", thrown.getMessage());

        verify(createUserService).execute(requestDTO);
    }

    @Test
    void shouldListAllUsersSuccessfully() {
        when(listUsersService.execute()).thenReturn(List.of(user));

        List<Users> result = userService.listAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(listUsersService).execute();
        verifyNoInteractions(createUserService);
    }


    @Test
    void shouldThrowExceptionWhenListUsersFails() {
        RuntimeException exception = new RuntimeException("Erro ao listar usuários");

        when(listUsersService.execute()).thenThrow(exception);

        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> userService.listAll()
        );

        assertEquals("Erro ao listar usuários", thrown.getMessage());

        verify(listUsersService).execute();
    }
}
