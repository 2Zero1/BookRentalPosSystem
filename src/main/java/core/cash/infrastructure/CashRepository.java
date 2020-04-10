package core.cash;

import core.user.User;

public interface CashRepository {
     int getOwnMoney(User user);
     boolean write(CashTransaction cashTransaction);
}
