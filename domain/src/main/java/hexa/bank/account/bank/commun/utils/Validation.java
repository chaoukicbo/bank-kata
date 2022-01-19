package hexa.bank.account.bank.commun.utils;


import hexa.bank.account.bank.commun.exception.InvalidArgumentException;

import java.util.Objects;

public class Validation {

    private Validation() {
    }

    public static void assertNonNull(Object object, String message) throws InvalidArgumentException {
        if (Objects.isNull(object)) {
            throw new InvalidArgumentException(message);
        }
    }
}
