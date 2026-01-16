package dev.Zerphyis.picpay.infra.configs;

import dev.Zerphyis.picpay.aplication.httpsmocks.AuthorizationClient;
import dev.Zerphyis.picpay.aplication.httpsmocks.AuthorizationService;
import dev.Zerphyis.picpay.aplication.usecases.*;
import dev.Zerphyis.picpay.domain.interfaceCases.*;
import dev.Zerphyis.picpay.domain.repositories.TransactionGateway;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public CreateUserInterface createUserUseCase(UserGateway userGateway) {
        return new CreateUsersImpl(userGateway);
    }

    @Bean
    public ListUserInterface listUsersUseCase(UserGateway userGateway) {return new ListUserImpl(userGateway);}

    @Bean
    public UserService userService(CreateUsersImpl createUserService, ListUserImpl listUsersService) {return new UserService(createUserService, listUsersService);}

    @Bean
    public VerifyUserInterface verifyUserUseCase(UserGateway userGateway) {
        return new VerifyUsersImpl(userGateway);
    }

    @Bean
    public TransferValidationInterface transferValidationUseCase() {
        return new TransferValidationImpl();
    }

    @Bean
    public BalanceValidateInterface balanceValidateUseCase() {
        return new BalanceValidateImpl();
    }

    @Bean
    public ValidateMechantUser validateMerchantUserUseCase() {
        return new ValidateMerchantUserImpl();
    }

    @Bean
    public TransferValueInterface transferUseCase(UserGateway userGateway, TransactionGateway transactionGateway) {return new TransferValueImpl(userGateway, transactionGateway);}

    @Bean
    public TransferService transferService(VerifyUsersImpl verifyUserExists, TransferValidationImpl verifyPayload, ValidateMerchantUserImpl verifyUserCanTransfer, BalanceValidateImpl verifySufficientBalance, AuthorizationService authorizationService, TransferValueImpl executeBalanceTransfer, NotifyTransferResult notifyTransferResult) {return new TransferService(verifyUserExists, verifyPayload, verifyUserCanTransfer, verifySufficientBalance, authorizationService, executeBalanceTransfer, notifyTransferResult);}

    @Bean
    public AuthorizationService authorizationService(AuthorizationClient authorizationClient) { return new AuthorizationService(authorizationClient);}




}
