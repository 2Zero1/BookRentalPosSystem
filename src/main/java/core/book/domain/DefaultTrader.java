package core.book.domain;

import core.book.application.BookLedger;
import core.cash.application.CashLedger;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.user.User;

public class DefaultTrader implements Trader{
    CashLedger cashLedger;
    BookLedger bookLedger;

    public DefaultTrader(CashLedger cashLedger, BookLedger bookLedger) {
        this.cashLedger = cashLedger;
        this.bookLedger = bookLedger;
}

    @Override
    public void trade(User user, Book book) {
        CashTransaction cashTransaction = cashLedger.write(user,book, CashTransactionType.OUTPUT);
        bookLedger.write(user.getUserNum(),book.getSerialNum(),cashTransaction.getCashTransactionNum(), BookTransactionType.RENT);
    }
}
