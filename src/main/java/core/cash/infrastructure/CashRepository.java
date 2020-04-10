package core.cash.infrastructure;

import core.cash.domain.CashTransaction;
import core.user.User;

public interface CashRepository {
     int getOwnMoney(User user);
     CashTransaction write(CashTransaction cashTransaction);
}
