package core.book.application;

import core.book.application.BookRentLedger;
import core.book.application.DefaultTrader;
import core.book.application.Library;
import core.book.domain.Book;
import core.book.domain.BookTransaction;
import core.book.domain.BookTransactionType;
import core.book.domain.Jenre;
import core.cash.application.CashLedger;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class DefaultTraderTest {
    DefaultTrader defaultTrader;
    @Mock
    CashLedger cashLedger;
    @Mock
    Library library;

    @Mock
    BookRentLedger bookRentLedger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        defaultTrader = new DefaultTrader(cashLedger, bookRentLedger, library);
    }

    @Test
    public void rentBook() {
        int bookSerialNum = 1;
        User user = new User(1,"이영한");
        Book book = new Book(1,bookSerialNum,"드래곤라자", Jenre.FANTASY, 300);
        CashTransaction cashTransaction = new CashTransaction(user.getUserNum(),bookSerialNum, CashTransactionType.OUTPUT);
        given(library.getBookBySerialNum(bookSerialNum)).willReturn(book);
        given(cashLedger.writeOutput(cashTransaction)).willReturn(cashTransaction);
        given(bookRentLedger.getLatestBookTransactionBySerial(bookSerialNum)).willReturn(Optional.of(new BookTransaction(user.getUserNum(),bookSerialNum, BookTransactionType.RETURN)));
        defaultTrader.rentBook(user,bookSerialNum);
        verify(bookRentLedger).writeTransaction(new BookTransaction(user.getUserNum(),bookSerialNum,cashTransaction.getCashTransactionNum(),BookTransactionType.RENT));

    }

    @Test
    public void returnBook() {
        int bookSerialNum = 1;
        int bookId = 1;
        int userNum = 1;
        String userName = "이영한";
        String bookName = "드래곤라자";
        User user = new User(userNum,userName);
        Book book = new Book(bookId, bookSerialNum, bookName, Jenre.FANTASY, 300);
        BookTransaction bookTransaction = new BookTransaction(userNum,bookSerialNum,BookTransactionType.RENT);
        given(library.searchBookBySerialNum(bookSerialNum)).willReturn(Optional.of(book));
        given(bookRentLedger.getLatestBookTransactionBySerial(bookSerialNum)).willReturn(Optional.of(bookTransaction));
        defaultTrader.returnBook(user, bookSerialNum);

    }

}