package core.cash.application;

import core.book.domain.Book;
import core.cash.domain.CashNotEnoughException;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.cash.infrastructure.CashRepository;
import core.user.domain.User;

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

    public CashTransaction writeOutput(CashTransaction cashTransaction) throws CashNotEnoughException {
//        if (!hasEnoughMoney(user,book.getPrice())) {
//            new CashNotEnoughException(book.getPrice());
//        }
//        return cashRepository.write(new CashTransaction(user.getUserNum(), book.getSerialNum(), book.getPrice(), CashTransactionType.OUTPUT));
        return cashRepository.write(cashTransaction);
    }

    public CashTransaction writeInput(CashTransaction cashTransaction) {

        return cashRepository.write(cashTransaction);
//        return cashRepository.write(new CashTransaction(user.getUserNum(),cash,CashTransactionType.INPUT));
    }

    public int getUserMoney(User user) {
        return cashRepository.getOwnMoney(user);
    }
}
