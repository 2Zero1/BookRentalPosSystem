package core.book.application;

public class BookEntityNotFoundException extends BookEntityException {
    int bookSerialNum;
    public BookEntityNotFoundException(int bookSerialNum) {
        super("해당 서적을 찾을 수 없습니다. (serialNumber: %d",bookSerialNum);
        this.bookSerialNum = bookSerialNum;
    }
}
