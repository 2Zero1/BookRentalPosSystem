package core.cash.application;

import core.book.domain.Book;
import core.book.domain.Jenre;
import core.cash.infrastructure.CashRepository;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CashLedgerTest {
    CashLedger cashLedger;

    @Mock
    CashRepository cashRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cashLedger = new CashLedger(cashRepository);
    }

    @Test
    public void hasEnoughMoney() {
        User user = new User("이영한");
        given(cashRepository.getOwnMoney(user)).willReturn(500);
        boolean rentResult = cashLedger.hasEnoughMoney(user,400);

        assertThat(rentResult).isEqualTo(true);
    }

    @Test
    public void hasNotEnoughMoney() {
        User user = new User("이영한");
        given(cashRepository.getOwnMoney(user)).willReturn(300);
        boolean rentResult = cashLedger.hasEnoughMoney(user,400);

        assertThat(rentResult).isEqualTo(false);
    }

    @Test
    public void writeOutputCashTransaction() {
        int userNum = 1;
        int bookSerialNum = 1;
        Book book = new Book(1,1,"드래곤라자", Jenre.FANTASY,300);

        cashLedger.writeOutput(new CashTransaction(userNum, bookSerialNum, CashTransactionType.OUTPUT));

        verify(cashRepository).write(new CashTransaction(userNum,book.getSerialNum(), CashTransactionType.INPUT));
    }

    @Test
    public void writeInputCashTransaction() {
        int userNum = 1;
        int cash = 10000;
        cashLedger.writeInput(new CashTransaction(userNum, cash, CashTransactionType.INPUT));
        verify(cashRepository).write(new CashTransaction(userNum,cash,CashTransactionType.INPUT));
    }

    @Test
    public void getUserMoney() {
        int money = 1000;
        User user = new User(1,"이영한");
        given(cashRepository.getOwnMoney(user)).willReturn(money);
        assertThat(cashLedger.getUserMoney(user)).isEqualTo(money);
    }
}