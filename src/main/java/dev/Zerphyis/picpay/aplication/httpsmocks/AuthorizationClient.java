package dev.Zerphyis.picpay.aplication.httpsmocks;

import dev.Zerphyis.picpay.aplication.dtos.AuthorizationResponseDto;
import dev.Zerphyis.picpay.aplication.exceptions.AuthorizationDeniedException;
import dev.Zerphyis.picpay.aplication.exceptions.AuthorizationServiceUnavailableException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class AuthorizationClient {
    private final RestTemplate restTemplate;
    private final String authorizationUrl;

    public AuthorizationClient(RestTemplate restTemplate, String authorizationUrl) {
        this.restTemplate = restTemplate;
        this.authorizationUrl = authorizationUrl;
    }


    public void authorize() {

        try {
            ResponseEntity<AuthorizationResponseDto> response =
                    restTemplate.getForEntity(
                            authorizationUrl,
                            AuthorizationResponseDto.class
                    );

            AuthorizationResponseDto body = response.getBody();

            if (body == null || !"AUTHORIZED".equalsIgnoreCase(body.status())) {
                throw new AuthorizationDeniedException();
            }

        } catch (RestClientException ex) {
            throw new AuthorizationServiceUnavailableException();
        }
    }
}
