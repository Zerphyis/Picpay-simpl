package dev.Zerphyis.picpay.infra.configs;

import dev.Zerphyis.picpay.aplication.httpsmocks.AuthorizationService;
import dev.Zerphyis.picpay.aplication.usecases.*;
import dev.Zerphyis.picpay.aplication.usecases.transfer.*;
import dev.Zerphyis.picpay.aplication.usecases.users.CreateUsersImpl;
import dev.Zerphyis.picpay.aplication.usecases.users.ListUserImpl;
import dev.Zerphyis.picpay.aplication.usecases.users.UserService;
import dev.Zerphyis.picpay.domain.repositories.TransactionGateway;
import dev.Zerphyis.picpay.domain.repositories.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public CreateUsersImpl createUsersImpl(UserGateway userGateway) {
        return new CreateUsersImpl(userGateway);
    }

    @Bean
    public ListUserImpl listUserImpl(UserGateway userGateway) {
        return new ListUserImpl(userGateway);
    }

    @Bean
    public UserService userService(CreateUsersImpl createUsersImpl, ListUserImpl listUserImpl) {
        return new UserService(createUsersImpl, listUserImpl);
    }

    @Bean
    public VerifyUsersImpl verifyUsersImpl(UserGateway userGateway) {
        return new VerifyUsersImpl(userGateway);
    }

    @Bean
    public ListAllTransactionImpl listAllTransactionsImpl(TransactionGateway transactionGateway) {
        return new ListAllTransactionImpl(transactionGateway);
    }

    @Bean
    public TransferValidationImpl transferValidationImpl() {
        return new TransferValidationImpl();
    }

    @Bean
    public BalanceValidateImpl balanceValidateImpl() {
        return new BalanceValidateImpl();
    }

    @Bean
    public ValidateMerchantUserImpl validateMerchantUserImpl() {
        return new ValidateMerchantUserImpl();
    }

    @Bean
    public TransferValueImpl transferValueImpl(UserGateway userGateway, TransactionGateway transactionGateway) {
        return new TransferValueImpl(userGateway, transactionGateway);
    }

    @Bean
    public NotifyTransferResult notifyTransferResult() {
        return new NotifyTransferResult();
    }

    @Bean
    public RefundCaseImpl refundCaseImpl(TransactionGateway transactionGateway, UserGateway userGateway) {
        return new RefundCaseImpl(transactionGateway, userGateway);
    }

}
