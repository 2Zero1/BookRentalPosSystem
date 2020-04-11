package core.book.domain;

import core.book.application.BookRentLedger;
import core.cash.application.CashLedger;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.user.User;

public class DefaultTrader implements Trader{
    CashLedger cashLedger;
    BookRentLedger bookRentLedger;

    public DefaultTrader(CashLedger cashLedger, BookRentLedger bookRentLedger) {
        this.cashLedger = cashLedger;
        this.bookRentLedger = bookRentLedger;
}

    @Override
    public void trade(User user, Book book) {
        CashTransaction cashTransaction = cashLedger.write(user,book, CashTransactionType.OUTPUT);
        bookRentLedger.write(user.getUserNum(),book.getSerialNum(),cashTransaction.getCashTransactionNum(), BookTransactionType.RENT);
    }
}
