package dev.Zerphyis.picpay.infra.controllers;

import dev.Zerphyis.picpay.aplication.dtos.CreateUserRequestDTO;
import dev.Zerphyis.picpay.aplication.usecases.users.UserService;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Users> create(@RequestBody CreateUserRequestDTO user) {
        Users created = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Users>> listAll() {
        return ResponseEntity.ok(userService.listAll());
    }
}
