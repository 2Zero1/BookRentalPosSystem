package core.book.application;

import core.book.domain.BookEntityException;
import core.book.domain.BookTransaction;
import core.book.infrastructure.BookLedgerRepository;

import java.util.Optional;

public class BookRentLedger {

    BookLedgerRepository bookLedgerRepository;

    public BookRentLedger(BookLedgerRepository bookLedgerRepository) {
        this.bookLedgerRepository = bookLedgerRepository;
    }

    public void writeTransaction(BookTransaction bookTransaction) throws BookEntityException {
        bookLedgerRepository.insertTx(bookTransaction);
    }

    public Optional<BookTransaction> getLatestBookTransactionBySerial(int bookSerialNum) {
        return bookLedgerRepository.getLatestTxBySerialNum(bookSerialNum);
    }
}
