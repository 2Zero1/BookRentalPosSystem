package core.user.domain.exception;

import common.SystemException;

public class UserEntityException extends SystemException {
    public UserEntityException(String foramt, Object... args) {
        super(foramt, args);
    }
}
