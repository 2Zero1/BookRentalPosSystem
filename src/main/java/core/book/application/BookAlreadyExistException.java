package core.book.application;

public class BookRegisterException extends BookEntityException {
    int bookSerialNum;

    public BookRegisterException(String foramt, Object... args) {
        super(foramt, args);
    }
}
