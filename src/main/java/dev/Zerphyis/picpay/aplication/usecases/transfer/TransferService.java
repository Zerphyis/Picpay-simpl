package dev.Zerphyis.picpay.aplication.usecases.transfer;

import dev.Zerphyis.picpay.aplication.httpsmocks.AuthorizationService;
import dev.Zerphyis.picpay.aplication.usecases.BalanceValidateImpl;
import dev.Zerphyis.picpay.aplication.usecases.NotifyTransferResult;
import dev.Zerphyis.picpay.domain.entities.users.Users;

import java.math.BigDecimal;

public class TransferService {

    private final VerifyUsersImpl verifyUsers;
    private final TransferValidationImpl transferValidation;
    private final ValidateMerchantUserImpl merchantValidation;
    private final BalanceValidateImpl balanceValidation;
    private final AuthorizationService authorizationService;
    private final TransferValueImpl transferValue;
    private final NotifyTransferResult notifyTransferResult;

    public TransferService(
            VerifyUsersImpl verifyUsers,
            TransferValidationImpl transferValidation,
            ValidateMerchantUserImpl merchantValidation,
            BalanceValidateImpl balanceValidation,
            AuthorizationService authorizationService,
            TransferValueImpl transferValue,
            NotifyTransferResult notifyTransferResult
    ) {
        this.verifyUsers = verifyUsers;
        this.transferValidation = transferValidation;
        this.merchantValidation = merchantValidation;
        this.balanceValidation = balanceValidation;
        this.authorizationService = authorizationService;
        this.transferValue = transferValue;
        this.notifyTransferResult = notifyTransferResult;
    }

    public void execute(
            Long payerId,
            Long payeeId,
            BigDecimal value
    ) {

        transferValidation.validate(payerId, payeeId, value);

        Users payer = verifyUsers.getExistingUser(payerId);
        Users payee = verifyUsers.getExistingUser(payeeId);

        merchantValidation.validate(payer);
        balanceValidation.validate(payer, value);
        authorizationService.authorize();

        transferValue.execute(payer, payee, value);

        notifyTransferResult.execute(payer.getId(), payee.getId());
    }
}
