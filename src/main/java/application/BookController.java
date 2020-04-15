package application;

import core.book.application.Library;
import core.book.domain.Book;
import core.book.domain.BookEntityException;
import core.book.domain.Trader;
import core.user.domain.User;

import java.util.List;

public class BookController {
    Library library;
    Trader trader;

    public BookController(Library library, Trader trader) {
        this.library = library;
        this.trader = trader;
    }

    public void rentBySerialNum(User user, int bookSerialNum) {
        trader.rentBook(user, bookSerialNum);
    }

    public void registerNewBook(Book book) throws BookEntityException {
        library.registerBook(book);
    }


    public void returnBook(User user, int serialNum) {
        trader.returnBook(user, serialNum);
    }

    public List<Book> getAllBooks() {
        return library.getAllBooks();
    }

    public List<Book> getBookByName(String bookName) {
        return library.getBookByName(bookName);
    }

    public Book getBookBySerialNum(int serialNum) {
        return library.getBookBySerialNum(serialNum);
    }

    public List<Book> getRentBookByUser(User user) {
        return library.getRentBookByUser(user);
    }
}
