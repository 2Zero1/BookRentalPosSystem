package core.cash.application;

import core.cash.domain.exception.CashNotEnoughException;
import core.cash.domain.CashTransaction;
import core.cash.infrastructure.CashRepository;
import core.user.domain.User;

public class CashLedger {
    CashRepository cashRepository ;

    public CashLedger(CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }


    public CashTransaction writeOutput(CashTransaction cashTransaction) throws CashNotEnoughException {
        return cashRepository.write(cashTransaction);
    }

    public CashTransaction writeInput(CashTransaction cashTransaction) {

        return cashRepository.write(cashTransaction);
    }

    public int getUserMoney(User user) {
        return cashRepository.getOwnMoney(user);
    }
}
