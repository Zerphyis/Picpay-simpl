package dev.Zerphyis.picpay.aplication.usecases.transfer;

import dev.Zerphyis.picpay.aplication.exceptions.MerchantTransferNotAllowedException;
import dev.Zerphyis.picpay.domain.entities.users.Users;
import dev.Zerphyis.picpay.domain.entities.users.UsersType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ValidateMerchantUserImplTest {
        private final ValidateMerchantUserImpl validator = new ValidateMerchantUserImpl();

        @Test
        void shouldThrowWhenUserIsMerchant() {
            Users user = new Users(
                    UsersType.MERCHANT,
                    BigDecimal.TEN,
                    "123",
                    "a@a.com",
                    "123",
                    "Merchant"
            );

            assertThrows(MerchantTransferNotAllowedException.class,
                    () -> validator.validate(user));
        }

        @Test
        void shouldPassWhenUserIsCommon() {
            Users user = new Users(
                    UsersType.COMMON,
                    BigDecimal.TEN,
                    "123",
                    "a@a.com",
                    "123",
                    "Common"
            );

            assertDoesNotThrow(() -> validator.validate(user));
        }
    }


