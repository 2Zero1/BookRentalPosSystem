package core.book.application;

import common.SystemException;

public class BookEntityException extends SystemException {

    public BookEntityException(String foramt, Object... args) {
        super(foramt, args);
    }

}
