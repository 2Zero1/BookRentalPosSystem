package core.book.application;

public class BookAlreadyExistException extends BookEntityException {
    int bookSerialNum;

    public BookAlreadyExistException(int bookSerialNum) {
        super("이미 사용중인 책넘버입니다. (bookSerial: %d", bookSerialNum);
        this.bookSerialNum = bookSerialNum;
    }
}
