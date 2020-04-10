package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class CashManagerTest {
    CashManager cashManager;

    @Mock
    CashRepository cashRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cashManager = new CashManager(cashRepository);
    }

    @Test
    public void hasEnoughMoney() {
        User user = new User("이영한");
        given(cashRepository.getOwnMoney(user)).willReturn(500);
        boolean rentResult = cashManager.hasEnoughMoney(user,400);

        assertThat(rentResult).isEqualTo(true);
    }

    @Test
    public void hasNotEnoughMoney() {
        User user = new User("이영한");
        given(cashRepository.getOwnMoney(user)).willReturn(300);
        boolean rentResult = cashManager.hasEnoughMoney(user,400);

        assertThat(rentResult).isEqualTo(false);
    }

}