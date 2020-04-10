package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class BookLedgerManagerTest {
    BookLedgerManager bookLedgerManager;
    @Mock
    BookTransactionManager bookTransactionManager;
    @Mock
    BookLedgerRepository bookLedgerRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        bookLedgerManager = new BookLedgerManager(bookTransactionManager,bookLedgerRepository);
    }

    @Test
    public void writeBookTx() {
        User user = new User(1,"이영한");
        Book book = new Book(1,1,"드래곤라자",Jenre.FANTASY, 300);
        BookTransaction bookTransaction = new BookTransaction(user.getUserNum(),book.getSerialNum(),TransactionType.RETURN);

        given(bookLedgerRepository.insertTx(bookTransaction)).willReturn(true);
        assertThat(bookLedgerManager.writeBookTx(bookTransaction)).isEqualTo(true);
    }

    @Test
    public void writeBookTxWithAlreadyRentBook() {
        User user = new User("이영한");
        Book book = new Book(1,1,"드래곤라자",Jenre.FANTASY, 300);
        BookTransaction bookTransaction = new BookTransaction(user.getUserNum(),book.getSerialNum(),TransactionType.RENT);
//        given(bookLedger.getLatestTxBySerialNum(book.getSerialNum())).willReturn(bookTransaction);
//        given(bookLedgerRepository.insertTx(bookTransaction)).willReturn(false);

        assertThat(bookLedgerManager.writeBookTx(bookTransaction)).isEqualTo(false);
    }


    @Test
    public void getLatestTx() {

        User user = new User("이영한");
        Book book = new Book(1,1,"드래곤라자",Jenre.FANTASY,400);
        BookTransaction bookTransaction = new BookTransaction(user.getUserNum(),book.getSerialNum(),TransactionType.RENT);

        given(bookLedgerRepository.getLatestTxBySerialNum(book.getSerialNum())).willReturn(bookTransaction);

        BookTransaction tx = bookLedgerManager.getLatestTxBySerialNum(book.getSerialNum());
        assertThat(tx.equals(bookTransaction)).isEqualTo(true);
    }

}