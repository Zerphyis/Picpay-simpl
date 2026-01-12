package dev.Zerphyis.picpay.aplication.usecases;

import dev.Zerphyis.picpay.aplication.exceptions.MerchantTransferNotAllowedException;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.entities.users.UsersType;
import dev.Zerphyis.picpay.domain.interfaceCases.ValidateMechantUser;

public class ValidateMerchantUserImpl implements ValidateMechantUser {

    @Override
    public void validate(Users payer) {
            if (payer.getUserType() == UsersType.MERCHANT) {
                throw new MerchantTransferNotAllowedException();
            }
        }
    }

