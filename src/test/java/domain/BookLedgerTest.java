//package domain;
//
//import common.RequestResult;
//import core.book.domain.Book;
//import core.book.application.BookLedger;
//import core.book.application.BookTransactionManager;
//import core.book.domain.Jenre;
//import core.book.infrastructure.BookLedgerRepository;
//import core.book.domain.BookTransaction;
//import core.book.domain.BookTransactionType;
//import core.user.domain.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.BDDMockito.given;
//
//class BookLedgerTest {
//    BookLedger bookLedger;
//    @Mock
//    BookTransactionManager bookTransactionManager;
//    @Mock
//    BookLedgerRepository bookLedgerRepository;
//
//    @BeforeEach
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//        bookLedger = new BookLedger(bookTransactionManager,bookLedgerRepository);
//    }
//
//    @Test
//    public void writeBookTx() {
//        User user = new User(1,"이영한");
//        Book book = new Book(1,1,"드래곤라자", Jenre.FANTASY, 300);
//        BookTransaction bookTransaction = new BookTransaction(user.getUserNum(),book.getSerialNum(),1, BookTransactionType.RETURN);
//        RequestResult bookTransactionResult =  RequestResult.Complete();
//        given(bookLedgerRepository.insertTx(bookTransaction)).willReturn(bookTransactionResult);
//
//        assertThat(bookLedger.write(bookTransaction)).isEqualTo(bookTransactionResult);
//    }
//
//    @Test
//    public void writeBookTxWithAlreadyRentBook() {
//        User user = new User("이영한");
//        Book book = new Book(1,1,"드래곤라자",Jenre.FANTASY, 300);
//        BookTransaction bookTransaction = new BookTransaction(user.getUserNum(),book.getSerialNum(),1, BookTransactionType.RENT);
//
//        RequestResult result =  RequestResult.Fail("이미 대출 장부에 대출중으로 기록된 서적이다");
//        given(bookLedgerRepository.insertTx(bookTransaction)).willReturn(result);
//
//        assertThat(bookLedger.write(bookTransaction)).isEqualTo(result);
//    }
//
//}