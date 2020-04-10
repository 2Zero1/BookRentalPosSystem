package core.cash.domain;

import common.SystemException;

public class CashEntityException extends SystemException {
    public CashEntityException(String foramt, Object... args) {
        super(foramt, args);
    }

    public CashEntityException(Throwable cause) {
        super(cause);
    }

    public CashEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Object[] getArguments() {
        return super.getArguments();
    }

    @Override
    public String getDefaultMessage() {
        return super.getDefaultMessage();
    }
}
