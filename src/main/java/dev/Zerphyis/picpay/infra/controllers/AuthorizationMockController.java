package dev.Zerphyis.picpay.infra.controllers;

import dev.Zerphyis.picpay.aplication.dtos.AuthorizationDataResponse;
import dev.Zerphyis.picpay.aplication.dtos.AuthorizationResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock/authorize")
public class AuthorizationMockController {
    @GetMapping
    public AuthorizationResponseDto authorize() {
        return new AuthorizationResponseDto(
                "success",
                new AuthorizationDataResponse(true)
        );
    }
}
