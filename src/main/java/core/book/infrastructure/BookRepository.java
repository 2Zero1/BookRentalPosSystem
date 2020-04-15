package core.book.infrastructure;

import core.book.domain.Book;
import core.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();
    List<Book> findByBookName(String bookName);
    Optional<Book> findBySerialNum(int serialNum);
    Book insertBook(Book book);

    List<Book> getAllBooks();

    List<Book> getBookByName(String bookName);

    Optional<Book> getBookBySerialNum(int serialNum);

    List<Book> getRentBookByUser(User user);
}
