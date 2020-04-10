package core.cash.application;

import core.book.domain.Book;
import core.cash.domain.CashNotEnoughException;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.cash.infrastructure.CashRepository;
import core.user.User;

public class CashLedger {
    CashRepository cashRepository ;

    public CashLedger(CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }

    public boolean hasEnoughMoney(User user, int price) {
        if(cashRepository.getOwnMoney(user) >= price){
            return true;
        }
        return false;
    }

    public CashTransaction write(User user, Book book, CashTransactionType cashTransaction) {
        if (!hasEnoughMoney(user,book.getPrice())) {
            new CashNotEnoughException(book.getPrice());
        }
        return cashRepository.write(new CashTransaction(user.getUserNum(), book.getSerialNum(), cashTransaction));
    }
}
