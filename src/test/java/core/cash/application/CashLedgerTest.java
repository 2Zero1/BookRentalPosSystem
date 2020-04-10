package core.cash.application;

import core.book.domain.Book;
import core.book.domain.Jenre;
import core.cash.application.CashLedger;
import core.cash.infrastructure.CashRepository;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
;
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
    public void writeInputCashTransaction() {
        int userNum = 1;
        int bookSerialNum = 1;
        User user = new User(1,"이영한");
        Book book = new Book(1,1,"드래곤라자", Jenre.FANTASY,300);
//        CashTransaction cashTransaction = new CashTransaction(userNum,bookSerialNum, CashTransactionType.INPUT);
//        given(cashRepository.write(cashTransaction)).willReturn(true);
        cashLedger.write(user,book, CashTransactionType.INPUT);
//        assertThat(cashLedger.write(cashTransaction)).isEqualTo(true);

        verify(cashRepository).write(new CashTransaction(userNum,book.getSerialNum(), CashTransactionType.INPUT));
    }

}