package core.book.application;

import core.book.domain.Book;
import core.book.domain.BookTransaction;
import core.book.domain.BookTransactionType;
import core.book.domain.Trader;
import core.book.infrastructure.BookRepository;
import core.cash.domain.CashNotEnoughException;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.user.User;
import core.cash.application.CashLedger;
import common.RequestResult;

import java.util.Optional;

public class BookManager {
    BookRepository bookRepository;
    Trader trader;

    public BookManager(BookRepository bookRepository, Trader trader) {
        this.bookRepository = bookRepository;
        this.trader = trader;
    }

    public void rentBySerialNum(User user, int bookSerialNum) {

        Book book = getBookBySerialNum(bookSerialNum);

        trader.trade(user, book);


    }

    public Book getBookBySerialNum(int bookSerialNum) {
        Book book = searchBookBySerialNum(bookSerialNum).orElseThrow(() -> new BookEntityNotFoundException(bookSerialNum));

        if (book.isRented()) {
            new BookAlreadyRentException(bookSerialNum);
        }
        return book;
    }

    public Optional<Book> searchBookBySerialNum(int bookSerialNum) {
        return bookRepository.findBySerialNum(bookSerialNum);
    }

    public void registerNewBook(Book book) {
        searchBookBySerialNum(book.getSerialNum()).
                ifPresent(v -> new BookAlreadyExistException(v.getSerialNum()));
        bookRepository.insertBook(book);
    }

}
