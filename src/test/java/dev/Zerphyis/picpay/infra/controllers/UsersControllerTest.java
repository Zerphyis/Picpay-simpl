package dev.Zerphyis.picpay.infra.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.Zerphyis.picpay.aplication.dtos.CreateUserRequestDTO;
import dev.Zerphyis.picpay.aplication.usecases.users.UserService;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.entities.users.UsersType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsersController.class)
@Import(HandleController.class)
class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateUserSuccessfully() throws Exception {
        CreateUserRequestDTO dto = new CreateUserRequestDTO(
                "User Test",
                "123",
                "test@email.com",
                "123",
                BigDecimal.TEN,
                "COMMON"
        );

        Users user = new Users(
                UsersType.COMMON,
                BigDecimal.TEN,
                "123",
                "test@email.com",
                "123",
                "User Test"
        );

        when(userService.create(any())).thenReturn(user);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullname").value("User Test"));

        verify(userService).create(any());
    }

    @Test
    void shouldReturn500WhenCreateUserFails() throws Exception {
        when(userService.create(any()))
                .thenThrow(new RuntimeException("Erro interno"));

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500));
    }

    @Test
    void shouldListUsersSuccessfully() throws Exception {
        when(userService.listAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk());

        verify(userService).listAll();
    }

    @Test
    void shouldReturn500WhenListUsersFails() throws Exception {
        when(userService.listAll())
                .thenThrow(new RuntimeException("Erro"));

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isInternalServerError());
    }
}