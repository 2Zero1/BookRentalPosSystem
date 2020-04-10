package core.book.application;

import core.book.application.BookTransactionManager;
import core.book.domain.BookTransaction;
import core.book.domain.BookTransactionType;
import core.book.infrastructure.BookLedgerRepository;
import common.RequestResult;

public class BookLedger {

    private BookTransactionManager bookTransactionManager;
    BookLedgerRepository bookLedgerRepository;

    public BookLedger(BookTransactionManager bookTransactionManager, BookLedgerRepository bookLedgerRepository) {
        this.bookTransactionManager = bookTransactionManager;
        this.bookLedgerRepository = bookLedgerRepository;
    }

    public void write(int userNum, int bookSerialNum, int cashTransactionNum, BookTransactionType bookTransactionType) {

        if (bookTransactionManager.isAvailable(bookSerialNum)) {
            new BookAlreadyRentException(bookSerialNum);
        }

        bookLedgerRepository.insertTx(new BookTransaction(userNum,bookSerialNum,cashTransactionNum,bookTransactionType));
    }

}
