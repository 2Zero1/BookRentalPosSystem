package core.book.application;

import core.book.domain.Book;
import core.book.domain.exception.BookAlreadyRentException;
import core.book.domain.exception.BookEntityNotFoundException;
import core.book.domain.exception.BookSerialNumAlreadyUsedException;
import core.book.infrastructure.BookRepository;
import core.user.domain.User;

import java.util.List;

public class Library {
    BookRepository bookRepository;

    public Library(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void registerBook(Book book) throws BookAlreadyRentException {

        checkExistSerialNum(book.getSerialNum());

        bookRepository.insertBook(book);
    }

    public void checkExistSerialNum(int serialNum) {
        bookRepository.findBySerialNum(serialNum).ifPresent(
                (v) -> {
                    throw new BookSerialNumAlreadyUsedException(serialNum);
                });
    }


    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public List<Book> getBookByName(String book) {
        return bookRepository.getBookByName(book);
    }

    public Book getBookBySerialNum(int serialNum) throws BookEntityNotFoundException {
        return bookRepository.getBookBySerialNum(serialNum).orElseThrow(() -> {
            throw new BookEntityNotFoundException(serialNum);
        });
    }

    public List<Book> getRentBookByUser(User user) {
        return bookRepository.getRentBookByUser(user);
    }
}
