package core.book;

import core.cash.BookLedgerRepository;
import core.cash.BookTransaction;
import common.RequestResult;

public class BookLedger {

    private BookTransactionManager bookTransactionManager;
    BookLedgerRepository bookLedgerRepository;

    public BookLedger(BookTransactionManager bookTransactionManager, BookLedgerRepository bookLedgerRepository) {
        this.bookTransactionManager = bookTransactionManager;
        this.bookLedgerRepository = bookLedgerRepository;
    }

    public RequestResult write(BookTransaction transaction) {

        if (bookTransactionManager.isAvailable(transaction)) {
            return RequestResult.Fail("이미 대출 장부에 대출중으로 기록된 서적이다");
        }

        return bookLedgerRepository.insertTx(transaction);
    }

}
