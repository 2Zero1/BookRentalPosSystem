package core.book.application;


import core.book.domain.BookTransaction;
import core.book.domain.BookTransactionType;
import core.book.infrastructure.BookLedgerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;



class BookRentLedgerTest {
    BookRentLedger bookRentLedger;
    @Mock
    BookLedgerRepository bookLedgerRepository;

    int userNum;
    int bookSerialNum;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookRentLedger = new BookRentLedger(bookLedgerRepository);
        userNum = 1;
        bookSerialNum = 1;
    }

    @Test
    public void writeRentBookTransaction() {
        int cashTransactionNum = 1;

        given(bookRentLedger.getLatestBookTransactionBySerial(1)).willReturn(Optional.of(new BookTransaction(userNum, bookSerialNum, BookTransactionType.RETURN)));

        bookRentLedger.writeTransaction(new BookTransaction(userNum, bookSerialNum, cashTransactionNum,BookTransactionType.RENT));

        verify(bookLedgerRepository).insertTx(new BookTransaction(userNum, bookSerialNum, cashTransactionNum, BookTransactionType.RENT));
    }


    @Test
    public void writeReturnBookTransaction() {

        bookRentLedger.writeTransaction(new BookTransaction(userNum, bookSerialNum,BookTransactionType.RENT));

        verify(bookLedgerRepository).insertTx(new BookTransaction(userNum, bookSerialNum, BookTransactionType.RENT));
    }

    @Test
    public void getLatestBookTransactionBySerial() {
        BookTransactionType bookTransactionType = BookTransactionType.RENT;
        BookTransaction mockBookTransaction = new BookTransaction(userNum, bookSerialNum, bookTransactionType);
        given(bookLedgerRepository.getLatestTxBySerialNum(1)).willReturn(Optional.of(mockBookTransaction));

        assertThat(bookRentLedger.getLatestBookTransactionBySerial(bookSerialNum)).isEqualTo(Optional.of(mockBookTransaction));
    }


}