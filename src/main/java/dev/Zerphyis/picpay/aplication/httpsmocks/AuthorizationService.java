package dev.Zerphyis.picpay.aplication.httpsmocks;

public class AuthorizationService {
    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }


    public void authorize() {
        authorizationClient.authorize();
    }
}
