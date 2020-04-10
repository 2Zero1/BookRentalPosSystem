package core.book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();
    List<Book> findByBookName(String bookName);
    Book findBySerialNum(int serialNum);
    boolean insertBook(Book book);
}
