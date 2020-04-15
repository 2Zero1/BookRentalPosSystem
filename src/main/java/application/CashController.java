package application;

import core.cash.application.CashLedger;
import core.user.application.UserManager;
import core.user.domain.User;

public class CashController {
    CashLedger cashLedger;
    UserManager userManager;

    public CashController(CashLedger cashLedger, UserManager userManager) {
        this.cashLedger = cashLedger;
        this.userManager = userManager;
    }

    public int getUserMoney(String userName) {
        User user = userManager.getUserByName(userName);
        return cashLedger.getUserMoney(user);
    }
}
