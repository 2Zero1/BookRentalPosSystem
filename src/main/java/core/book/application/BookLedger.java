package core.book.application;

import core.book.domain.exception.BookEntityException;
import core.book.domain.BookTransaction;
import core.book.infrastructure.BookLedgerRepository;

public class BookLedger {

    BookLedgerRepository bookLedgerRepository;

    public BookLedger(BookLedgerRepository bookLedgerRepository) {
        this.bookLedgerRepository = bookLedgerRepository;
    }

    public void writeTransaction(BookTransaction bookTransaction) throws BookEntityException {
        bookLedgerRepository.insertTx(bookTransaction);
    }

}
