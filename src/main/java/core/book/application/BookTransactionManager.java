package core.book;

import core.cash.BookLedgerRepository;
import core.cash.BookTransaction;
import core.cash.TransactionType;

public class BookTransactionManager {

    BookLedgerRepository bookLedgerRepository;

    public BookTransactionManager(BookLedgerRepository bookLedgerRepository) {
        this.bookLedgerRepository = bookLedgerRepository;
    }

    public boolean isAvailable(BookTransaction bookTransaction) {
        BookTransaction latestBookTx = getLatestTxBySerialNum(bookTransaction.getBookSerialNum());
        return latestBookTx.getTxType() == TransactionType.RENT;
    }

    private BookTransaction getLatestTxBySerialNum(int serialNum) {
        return bookLedgerRepository.getLatestTxBySerialNum(serialNum);
    }
}
