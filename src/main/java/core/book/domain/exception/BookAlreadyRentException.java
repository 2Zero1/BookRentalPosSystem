package core.book.domain.exception;

public class BookAlreadyRentException extends BookEntityException {
    int bookSerialNum;
    public BookAlreadyRentException(int bookSerialNum) {
        super("이미 대출된 서적입니다. (책 시리얼넘버: %d", bookSerialNum);
        this.bookSerialNum = bookSerialNum;
    }
}
