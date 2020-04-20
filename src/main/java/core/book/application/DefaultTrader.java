package core.book.application;

import core.book.domain.*;
import core.book.domain.exception.BookAlreadyRentException;
import core.book.domain.exception.BookAlreadyReturnException;
import core.cash.application.CashLedger;
import core.cash.domain.exception.CashNotEnoughException;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.user.domain.User;

public class DefaultTrader implements Trader {
    CashLedger cashLedger;
    BookLedger bookLedger;
    Library library;

    public DefaultTrader(CashLedger cashLedger, BookLedger bookLedger
            , Library library) {
        this.cashLedger = cashLedger;
        this.bookLedger = bookLedger;
        this.library = library;
}

    @Override
    public void rentBook(User user, int bookSerialNum) {
        Book book = library.getBookBySerialNum(bookSerialNum);

        if (book.isRented()) throw new BookAlreadyRentException(bookSerialNum);

        if (book.getPrice() > user.getCash()) {
            throw new CashNotEnoughException(user.getCash(), book.getPrice());
        }

        CashTransaction cashTransaction = cashLedger.writeOutput(
                new CashTransaction(
                        user.getUserNum(),bookSerialNum,book.getPrice()
                        , CashTransactionType.OUTPUT
                )
        );

        bookLedger.writeTransaction(
                new BookTransaction(user.getUserNum(),book.getSerialNum()
                        ,cashTransaction.getCashTransactionNum()
                        ,BookTransactionType.RENT)
        );
    }

    @Override
    public void returnBook(User user, int serialNum) {

        Book book = library.getBookBySerialNum(serialNum);

        //책이 빌려진 상태인지 확인.
        if (!book.isRented()) {
            throw new BookAlreadyReturnException(serialNum);
        }

        bookLedger.writeTransaction(new BookTransaction(user.getUserNum(),serialNum,BookTransactionType.RETURN));

    }
}
