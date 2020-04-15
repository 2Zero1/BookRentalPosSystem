package core.cash.infrastructure;

import core.cash.domain.CashTransaction;
import core.user.domain.User;

import java.util.List;

public interface CashRepository {
     int getOwnMoney(User user);

     CashTransaction write(CashTransaction cashTransaction);
}
