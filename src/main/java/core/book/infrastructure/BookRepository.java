package core.book.infrastructure;

import core.book.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();
    List<Book> findByBookName(String bookName);
    Optional<Book> findBySerialNum(int serialNum);
    Book insertBook(Book book);
}
