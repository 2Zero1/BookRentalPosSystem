package core.book.application;


import core.book.domain.BookTransaction;
import core.book.domain.BookTransactionType;
import core.book.infrastructure.BookLedgerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


class BookLedgerTest {
    BookLedger bookLedger;
    @Mock
    BookLedgerRepository bookLedgerRepository;

    int userNum;
    int bookSerialNum;
    int cashTransactionNum = 1;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookLedger = new BookLedger(bookLedgerRepository);
        userNum = 1;
        bookSerialNum = 1;
    }

    @Test
    public void writeRentBookTransaction() {


        bookLedger.writeTransaction(new BookTransaction(userNum, bookSerialNum, cashTransactionNum, BookTransactionType.RENT));

        verify(bookLedgerRepository).insertTx(new BookTransaction(userNum, bookSerialNum, cashTransactionNum, BookTransactionType.RENT));
    }


    @Test
    public void writeReturnBookTransaction() {

        bookLedger.writeTransaction(new BookTransaction(userNum, bookSerialNum, BookTransactionType.RENT));

        verify(bookLedgerRepository).insertTx(new BookTransaction(userNum, bookSerialNum, BookTransactionType.RENT));
    }

}
