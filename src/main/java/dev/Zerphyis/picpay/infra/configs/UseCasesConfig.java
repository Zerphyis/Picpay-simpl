package dev.Zerphyis.picpay.infra.configs;

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
    public TransferValueImpl transferUseCase(UserGateway userGateway, TransactionGateway transactionGateway) {
        return new TransferValueImpl(userGateway, transactionGateway);
    }
}
