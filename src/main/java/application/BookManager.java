package application;

import core.book.application.BookAlreadyExistException;
import core.book.application.BookAlreadyRentException;
import core.book.application.BookEntityNotFoundException;
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

// TODO: 책 서비스로 변경. 책 등록, 검색 , 하는 부분은 모두 Library로 보내기. 그리고 책 등록, 반납 처리, 책 검색 API 만 만들기.
// 반납 처리를 이곳에서 .
public class BookManager {
    BookRepository bookRepository;
    Trader trader;

    public BookManager(BookRepository bookRepository, Trader trader) {
        this.bookRepository = bookRepository;
        this.trader = trader;
    }

    public void rentBySerialNum(User user, int bookSerialNum) {
        Book book = searchBookBySerialNum(bookSerialNum).orElseThrow(() -> new BookEntityNotFoundException(bookSerialNum));
//        Book book = getBookBySerialNum(bookSerialNum);
        if (book.isRented()) {
            new BookAlreadyRentException(bookSerialNum);
        }

        trader.trade(user, book);
    }

    public Book getBookBySerialNum(int bookSerialNum) {
        Optional<Book> option = bookRepository.findBySerialNum(bookSerialNum);

        return option.orElseThrow(() -> new BookEntityNotFoundException(bookSerialNum));
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
