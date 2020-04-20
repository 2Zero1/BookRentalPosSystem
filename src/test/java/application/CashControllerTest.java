package application;

import core.cash.application.CashLedger;
import core.user.application.UserManager;
import core.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;

class CashControllerTest {
    CashController cashController;
    @Mock
    CashLedger cashLedger;
    @Mock
    UserManager userManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cashController = new CashController(cashLedger, userManager);
    }

    @Test
    public void getUserMoney() {
        String userName = "이영한";
        User user = new User(1,userName);
        given(cashLedger.getUserMoney(user)).willReturn(1000);
        given(userManager.getUserByName(userName)).willReturn(user);
    }

}