package core.user.domain;

import common.SystemException;

public class UserEntityException extends SystemException {
    public UserEntityException(String foramt, Object... args) {
        super(foramt, args);
    }
}
