package core.book.application;


import core.book.domain.*;
import core.book.infrastructure.BookRepository;
import core.cash.application.CashLedger;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


class BookManagerTest {
    @Mock
    BookRepository bookRepository;
    @Mock
    CashLedger cashLedger;
    @Mock
    BookLedger bookLedger;

    @Mock
    Trader trader;

    BookManager bookManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        trader = new DefaultTrader(cashLedger, bookLedger);
        bookManager = new BookManager(bookRepository, trader);
    }

    @Test
    public void rentExistBookByBookSerialNum() {

        User user = new User("이영한");
        int bookSerialNum = 1;
        CashTransaction cashTransaction = new CashTransaction(1);
        Book book = new Book(1, bookSerialNum, "드래곤라자", Jenre.FANTASY, 400);
        given(bookRepository.findBySerialNum(bookSerialNum)).willReturn(Optional.of(book));
        given(cashLedger.write(user, book, CashTransactionType.OUTPUT)).willReturn(cashTransaction);

        bookManager.rentBySerialNum(user, book.getSerialNum());
        //TODO : 왜 에러나는지 확인좀 해보기.
//        verify(trader).trade(user,book);
    }
//    @Test
//    public void rentExistBook() {
//
//        User user = new User("이영한");
//
//        Book book = new Book(1, 1, "드래곤라자", Jenre.FANTASY, 400);
//
//        given(bookRepository.findBySerialNum(1)).willReturn(Optional.of(book));
//        given(cashLedger.hasEnoughMoney(user, book.getPrice())).willReturn(true);
//        given(cashLedger.write(user, book, CashTransactionType.OUTPUT)).willReturn(new CashTransaction(user.getUserNum(), book.getSerialNum(), CashTransactionType.OUTPUT));
//        int bookSerialNum = 1;
//        boolean requestResult = bookManager.rent(user, bookSerialNum);
//        assertThat(requestResult).isEqualTo(true);
//    }

    @Test
    public void rentNonExistBook() {

        User user = new User("이영한");

        int bookSerialNum2 = 2;
        Book book = new Book(1, bookSerialNum2, "드래곤라자", Jenre.FANTASY, 400);

        CashTransaction cashTransaction = new CashTransaction(1);

        given(bookRepository.findBySerialNum(1)).willReturn(Optional.of(book));
        given(cashLedger.hasEnoughMoney(user, book.getPrice())).willReturn(true);
        given(cashLedger.write(user, book, CashTransactionType.OUTPUT))
                .willReturn(cashTransaction);

//        boolean requestResult = bookManager.rent(user, book.getSerialNum());

//        verify(cashLedger).write(user.getUserNum(),book.getSerialNum(),CashTransactionType.OUTPUT);
//        verify(bookLedger).write(user.getUserNum(),book.getSerialNum(),cashTransaction.getCashTransactionNum(),BookTransactionType.RENT);
        //        boolean requestResult = bookManager.rent(user, bookSerialNum2);
//        assertThat(requestResult).isEqualTo(false);
    }


    //TODO :여기서 이걸 테스트하는게 맞나 싶다 생각해봐야함
//    @Test
//    public void rentBookWithoutEnoughMoney() {
//
//        User user = new User("이영한");
//
//        Book book = new Book(1,1,"드래곤라자",Jenre.FANTASY ,400);
//
//        given(bookRepository.findBySerialNum(1)).willReturn(Optional.of(book));
//        given(cashLedger.hasEnoughMoney(user,book.getPrice())).willReturn(false);
//
//        int bookSerialNum = 1;
//
//        boolean requestResult = bookManager.rent(user, bookSerialNum);
//    }

    @Test
    public void registerNewBook() {
        int serialNum = 1;
        Book book = new Book(1, serialNum, "바키", Jenre.MARTIAL_ARTS, 400);

        given(bookManager.searchBookBySerialNum(serialNum)).willReturn(Optional.empty());

        bookManager.registerNewBook(book);

        verify(bookRepository).insertBook(book);
//        RequestResult result = bookManager.registerNewBook(book);
//        assertThat(result.isComplete()).isEqualTo(true);
    }

    @Test
    public void registerNewBookWithExistSerialNum() {
        int serialNum = 1;
        Book book = new Book(1, serialNum, "바키", Jenre.MARTIAL_ARTS, 400);

        given(bookManager.searchBookBySerialNum(serialNum)).willReturn(Optional.of(book));
        bookManager.registerNewBook(book);
//        assertThat(bookManager.registerNewBook(book)).isEqualTo(false);
        verify(bookRepository).insertBook(book);
    }

}
