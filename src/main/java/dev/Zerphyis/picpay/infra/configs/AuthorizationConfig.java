package dev.Zerphyis.picpay.infra.configs;

import dev.Zerphyis.picpay.aplication.httpsmocks.AuthorizationClient;
import dev.Zerphyis.picpay.aplication.httpsmocks.AuthorizationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AuthorizationConfig {

    @Value("${external.authorization.url}")
    private String authorizationUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public AuthorizationClient authorizationClient(RestTemplate restTemplate) {
        return new AuthorizationClient(restTemplate, authorizationUrl);
    }

    @Bean
    public AuthorizationService authorizationService(
            AuthorizationClient authorizationClient
    ) {
        return new AuthorizationService(authorizationClient);
    }
}
