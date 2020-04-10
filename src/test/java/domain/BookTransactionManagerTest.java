//package domain;
//
//import core.book.application.BookTransactionManager;
//import core.book.infrastructure.BookLedgerRepository;
//import core.book.domain.BookTransaction;
//import core.book.domain.BookTransactionType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.BDDMockito.given;
//
//class BookTransactionManagerTest {
//    BookTransactionManager bookTransactionManager;
//    @Mock
//    BookLedgerRepository bookLedgerRepository;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        bookTransactionManager = new BookTransactionManager(bookLedgerRepository);
//    }
//    @Test
//    public void isAvailable(){
//        BookTransaction bookTransaction = new BookTransaction(1,1,1, BookTransactionType.RENT);
//        given(bookLedgerRepository.getLatestTxBySerialNum(bookTransaction.getBookSerialNum())).willReturn(bookTransaction);
//        assertThat(bookTransactionManager.isAvailable(bookTransaction)).isEqualTo(true);
//    }
//
//
//
//
//}