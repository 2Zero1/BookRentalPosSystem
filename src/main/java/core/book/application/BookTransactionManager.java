package core.book.application;

import core.book.infrastructure.BookLedgerRepository;
import core.book.domain.BookTransaction;
import core.book.domain.BookTransactionType;

public class BookTransactionManager {

    BookLedgerRepository bookLedgerRepository;

    public BookTransactionManager(BookLedgerRepository bookLedgerRepository) {
        this.bookLedgerRepository = bookLedgerRepository;
    }

    public boolean isAvailable(int bookSerialNum) {
        BookTransaction latestBookTx = getLatestTxBySerialNum(bookSerialNum);
        return latestBookTx.getTxType() == BookTransactionType.RENT;
    }

    private BookTransaction getLatestTxBySerialNum(int serialNum) {
        return bookLedgerRepository.getLatestTxBySerialNum(serialNum);
    }
}
