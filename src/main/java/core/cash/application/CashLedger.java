package core.cash;

import core.cash.CashRepository;
import core.cash.CashTransaction;
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

    public boolean write(CashTransaction cashTransaction) {

        return cashRepository.write(cashTransaction);
    }
}
