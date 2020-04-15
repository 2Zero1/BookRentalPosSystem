package core.book.application;

import core.book.application.BookRentLedger;
import core.book.application.Library;
import core.book.domain.*;
import core.cash.application.CashLedger;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.user.domain.User;

public class DefaultTrader implements Trader {
    CashLedger cashLedger;
    BookRentLedger bookRentLedger;
    Library library;

    public DefaultTrader(CashLedger cashLedger, BookRentLedger bookRentLedger, Library library) {
        this.cashLedger = cashLedger;
        this.bookRentLedger = bookRentLedger;
        this.library = library;
}

    @Override
    public void rentBook(User user, int bookSerialNum) {
        //사람 돈이 충분히 있는지 확인
        //책이 존재하는지
        Book book = library.getBookBySerialNum(bookSerialNum);
        if (book.isRented()) throw new BookAlreadyRentException(bookSerialNum);
        //책이 이미 빌려졌는지 확인.
        bookRentLedger.getLatestBookTransactionBySerial(bookSerialNum).ifPresent(v -> {
            if (v.getTxType()== BookTransactionType.RENT)
                throw new BookAlreadyReturnException(bookSerialNum);
        });

        CashTransaction cashTransaction = cashLedger.writeOutput(new CashTransaction(user.getUserNum(),bookSerialNum,book.getPrice(), CashTransactionType.OUTPUT));

        bookRentLedger.writeTransaction(new BookTransaction(user.getUserNum(),book.getSerialNum(),cashTransaction.getCashTransactionNum(),BookTransactionType.RENT));
    }

    @Override
    public void returnBook(User user, int serialNum) {
        Book book = library.searchBookBySerialNum(
                serialNum).orElseThrow(
                        () -> {
                            throw new BookEntityNotFoundException(serialNum);
                        });

        bookRentLedger.getLatestBookTransactionBySerial(serialNum).ifPresent(v -> {
            if (v.getTxType()== BookTransactionType.RETURN)
                throw new BookAlreadyReturnException(serialNum);
        });

        if (!book.isRented()) {
            new BookAlreadyReturnException(serialNum);
        }
        bookRentLedger.writeTransaction(new BookTransaction(user.getUserNum(),serialNum,BookTransactionType.RETURN));

    }
}
