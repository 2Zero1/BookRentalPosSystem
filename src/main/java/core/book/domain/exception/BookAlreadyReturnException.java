package core.book.domain.exception;

public class BookAlreadyReturnException extends BookEntityException {
    private int bookSerialNum;

    public BookAlreadyReturnException(int bookSerialNum) {
        super("이미 대출된 서적입니다. (책 시리얼넘버: %d", bookSerialNum);
        this.bookSerialNum = bookSerialNum;
    }
}
