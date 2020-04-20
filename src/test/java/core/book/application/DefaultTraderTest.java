package core.book.application;

import core.book.domain.*;
import core.book.domain.exception.BookAlreadyRentException;
import core.book.domain.exception.BookAlreadyReturnException;
import core.book.domain.exception.BookEntityNotFoundException;
import core.cash.application.CashLedger;
import core.cash.domain.exception.CashNotEnoughException;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


class DefaultTraderTest {
    DefaultTrader defaultTrader;
    @Mock
    CashLedger cashLedger;
    @Mock
    Library library;

    @Mock
    BookLedger bookLedger;

    User user;
    Book book;
    int enough_cash = 1000;
    int notEnough_cash = 100;
    int bookSerialNum = 1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        defaultTrader = new DefaultTrader(cashLedger, bookLedger, library);

        user = new User(1,"이영한",enough_cash);
        book = new Book(1,1,"드래곤라자", Jenre.FANTASY, 300);

    }

    @Test
    public void rentBook() {
        CashTransaction cashTransaction = new CashTransaction(user.getUserNum(),bookSerialNum, CashTransactionType.OUTPUT);
        given(library.getBookBySerialNum(bookSerialNum)).willReturn(book);
        given(cashLedger.writeOutput(cashTransaction)).willReturn(cashTransaction);

        defaultTrader.rentBook(user,bookSerialNum);

        verify(bookLedger).writeTransaction(new BookTransaction(user.getUserNum(),bookSerialNum,cashTransaction.getCashTransactionNum(),BookTransactionType.RENT));
    }

    @Test
    public void rentNonExistBook() {
        given(library.getBookBySerialNum(bookSerialNum)).willThrow(BookEntityNotFoundException.class);

        assertThatThrownBy(() -> defaultTrader.rentBook(user,bookSerialNum)).isInstanceOf(BookEntityNotFoundException.class);
    }


    @Test
    public void rentBookWithOutEnoughMoney() {
        user.setCash(notEnough_cash);

        given(library.getBookBySerialNum(bookSerialNum)).willReturn(book);

        assertThatThrownBy(() -> defaultTrader.rentBook(user,bookSerialNum))
                .isInstanceOf(CashNotEnoughException.class);
    }

    @Test
    public void rentAlreadyRentBook() {
        book.setRented(true);

        given(library.getBookBySerialNum(bookSerialNum)).willReturn(book);

        assertThatThrownBy(() -> defaultTrader.rentBook(user,bookSerialNum))
                .isInstanceOf(BookAlreadyRentException.class);
    }

    @Test
    public void returnBook() {
        book.setRented(true);

        given(library.getBookBySerialNum(bookSerialNum)).willReturn(book);

        defaultTrader.returnBook(user, book.getSerialNum());
    }

    @Test
    public void returnAlreadyReturnedBook() {
        book.setRented(false);

        given(library.getBookBySerialNum(bookSerialNum)).willReturn(book);

        assertThatThrownBy(
                () -> defaultTrader.returnBook(user, book.getSerialNum()))
                .isInstanceOf(BookAlreadyReturnException.class);
    }

}