package application;



import core.book.application.BookRentLedger;
import core.book.application.DefaultTrader;
import core.book.application.Library;
import core.book.domain.*;
import core.cash.application.CashLedger;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


class BookControllerTest {
    @Mock
    Library library;

    @Mock
    CashLedger cashLedger;
    @Mock
    BookRentLedger bookRentLedger;

    @Mock
    Trader trader;

    BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookController = new BookController(library, trader);
    }

    @Test
    public void rentExistBookByBookSerialNum() {

        User user = new User("이영한");
        int bookSerialNum = 1;
        Book book = new Book(1, bookSerialNum, "드래곤라자", Jenre.FANTASY, 400);

//        CashTransaction cashTransaction = new CashTransaction(user.getUserNum(),bookSerialNum,book.getPrice(), CashTransactionType.OUTPUT);
//        given(cashLedger.writeOutput(cashTransaction)).willReturn(cashTransaction);
//        given(library.getBookBySerialNum(bookSerialNum)).willReturn(book);
        bookController.rentBySerialNum(user, bookSerialNum);
        verify(trader).rentBook(user, bookSerialNum);

    }

    @Test
    public void getBookByName() {
        String bookName = "드래곤라자";
        List<Book> books = List.of(new Book(1,1,bookName,Jenre.FANTASY,300),
                new Book(1,2,bookName,Jenre.FANTASY,300));
        given(library.getBookByName(bookName)).willReturn(books);
        assertThat(bookController.getBookByName(bookName)).isEqualTo(books);
    }

    @Test
    public void getRentBookByUser() {
        User user = new User(1,"이영한");
        List<Book> mockBooks = List.of(new Book(1,1,"드래곤라자",Jenre.FANTASY,300));
        given(library.getRentBookByUser(user)).willReturn(mockBooks);
        assertThat(bookController.getRentBookByUser(user)).isEqualTo(mockBooks);
    }

    @Test
    public void registerNewBook() {
        int serialNum = 1;
        given(library.searchBookBySerialNum(serialNum)).willReturn(null);
        Book book = new Book(1, serialNum, "바키", Jenre.MARTIAL_ARTS, 400);

        bookController.registerNewBook(book);

        verify(library).registerBook(book);
    }

    @Test
    public void returnBook() {
        //TODO:DB에 그냥 책 통째로 넣어야함. 나중에 user도 그렇고
        User user = new User(1, "이영한");
        int serialNum = 1;

        bookController.returnBook(user,serialNum);

        verify(trader).returnBook(user,serialNum);
    }

    @Test
    public void getAllBook() {
        List<Book> books = List.of(new Book(1,1,"드래곤라자",Jenre.FANTASY,300),
                new Book(2,2,"도라에몽",Jenre.FANTASY,400));
        given(library.getAllBooks()).willReturn(books);
        assertThat(bookController.getAllBooks()).isEqualTo(books);
    }

    @Test
    public void getBookBySerialNum() {
        Book book = new Book(1,1,"드래곤라자",Jenre.FANTASY,300);
        given(library.getBookBySerialNum(book.getSerialNum())).willReturn(book);
        assertThat(bookController.getBookBySerialNum(book.getSerialNum())).isEqualTo(book);
    }

}
