package core.book.domain.exception;

public class BookSerialNumAlreadyUsedException extends BookEntityException {
    int bookSerialNum;

    public BookSerialNumAlreadyUsedException(int bookSerialNum) {
        super("이미 사용중인 책넘버입니다. (bookSerial: %d", bookSerialNum);
        this.bookSerialNum = bookSerialNum;
    }
}
