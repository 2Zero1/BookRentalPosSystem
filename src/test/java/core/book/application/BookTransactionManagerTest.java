package core.book.application;

import core.book.application.BookTransactionManager;
import core.book.infrastructure.BookLedgerRepository;
import core.book.domain.BookTransaction;
import core.book.domain.BookTransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class BookTransactionManagerTest {
    BookTransactionManager bookTransactionManager;
    @Mock
    BookLedgerRepository bookLedgerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookTransactionManager = new BookTransactionManager(bookLedgerRepository);
    }
    @Test
    public void isAvailable(){
        int userNum = 1;
        int bookSerialNum = 1;
        int cashTransactionNum = 1;

        BookTransaction bookTransaction = new BookTransaction(userNum,bookSerialNum,cashTransactionNum, BookTransactionType.RETURN);
        given(bookLedgerRepository.getLatestTxBySerialNum(bookTransaction.getBookSerialNum())).willReturn(bookTransaction);
        assertThat(bookTransactionManager.isAvailable(bookSerialNum)).isEqualTo(true);
    }




}