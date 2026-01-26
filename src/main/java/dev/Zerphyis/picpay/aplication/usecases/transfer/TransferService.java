package dev.Zerphyis.picpay.aplication.usecases.transfer;

import dev.Zerphyis.picpay.aplication.httpsmocks.AuthorizationService;
import dev.Zerphyis.picpay.aplication.usecases.BalanceValidateImpl;
import dev.Zerphyis.picpay.aplication.usecases.NotifyTransferResult;
import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public class TransferService {

    private final VerifyUsersImpl verifyUsers;
    private final TransferValidationImpl transferValidation;
    private final ValidateMerchantUserImpl merchantValidation;
    private final BalanceValidateImpl balanceValidation;
    private final AuthorizationService authorizationService;
    private final TransferValueImpl transferValue;
    private final NotifyTransferResult notifyTransferResult;
    private final RefundCaseImpl refundCase;
    private final ListAllTransactionImpl listAllTransactions;

    public TransferService(
            VerifyUsersImpl verifyUsers,
            TransferValidationImpl transferValidation,
            ValidateMerchantUserImpl merchantValidation,
            BalanceValidateImpl balanceValidation,
            AuthorizationService authorizationService,
            TransferValueImpl transferValue,
            NotifyTransferResult notifyTransferResult,
            RefundCaseImpl refundCase,
            ListAllTransactionImpl listAllTransactions
    ) {
        this.verifyUsers = verifyUsers;
        this.transferValidation = transferValidation;
        this.merchantValidation = merchantValidation;
        this.balanceValidation = balanceValidation;
        this.authorizationService = authorizationService;
        this.transferValue = transferValue;
        this.notifyTransferResult = notifyTransferResult;
        this.refundCase = refundCase;
        this.listAllTransactions = listAllTransactions;
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

    public void refund(Long transactionId) {
        refundCase.execute(transactionId);
    }
    @Transactional(readOnly = true)
    public List<Transaction> listAllTransactions() {
        return listAllTransactions.execute();
    }
}
