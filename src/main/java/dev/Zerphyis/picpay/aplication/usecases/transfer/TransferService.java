package dev.Zerphyis.picpay.aplication.usecases.transfer;

import dev.Zerphyis.picpay.aplication.httpsmocks.AuthorizationService;
import dev.Zerphyis.picpay.aplication.usecases.BalanceValidateImpl;
import dev.Zerphyis.picpay.aplication.usecases.NotifyTransferResult;
import dev.Zerphyis.picpay.domain.entities.users.Users;

import java.math.BigDecimal;

public class TransferService {

    private final VerifyUsersImpl verifyUserExists;
    private final TransferValidationImpl verifyPayload;
    private final ValidateMerchantUserImpl verifyUserCanTransfer;
    private final BalanceValidateImpl verifySufficientBalance;
    private final AuthorizationService verifyAuthorizationService;
    private final TransferValueImpl executeBalanceTransfer;
    private final NotifyTransferResult notifyTransferResult;

    public TransferService(
            VerifyUsersImpl verifyUserExists,
            TransferValidationImpl verifyPayload,
            ValidateMerchantUserImpl verifyUserCanTransfer,
            BalanceValidateImpl verifySufficientBalance,
            AuthorizationService verifyAuthorizationService,
            TransferValueImpl executeBalanceTransfer,
            NotifyTransferResult notifyTransferResult
    ) {
        this.verifyUserExists = verifyUserExists;
        this.verifyPayload = verifyPayload;
        this.verifyUserCanTransfer = verifyUserCanTransfer;
        this.verifySufficientBalance = verifySufficientBalance;
        this.verifyAuthorizationService = verifyAuthorizationService;
        this.executeBalanceTransfer = executeBalanceTransfer;
        this.notifyTransferResult = notifyTransferResult;
    }

    public void execute(
            Long PayerId,
            Long PayeeId,
            BigDecimal value
    ) {

        verifyPayload.validate(PayerId, PayeeId, value);

        Users payer = verifyUserExists.validateAndGet(PayerId);
        Users payee = verifyUserExists.validateAndGet(PayeeId);

        verifyUserCanTransfer.validate(payer);
        verifySufficientBalance.validate(payer, value);
        verifyAuthorizationService.authorize();

        executeBalanceTransfer.execute(payer.getId(), payee.getId(), value);

        notifyTransferResult.execute(PayerId, PayeeId);
    }
}
