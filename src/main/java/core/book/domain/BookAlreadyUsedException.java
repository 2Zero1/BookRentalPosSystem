package core.book.domain;

public class BookAlreadyUsedException extends BookEntityException {
    int bookSerialNum;

    public BookAlreadyUsedException(int bookSerialNum) {
        super("이미 사용중인 책넘버입니다. (bookSerial: %d", bookSerialNum);
        this.bookSerialNum = bookSerialNum;
    }
}
