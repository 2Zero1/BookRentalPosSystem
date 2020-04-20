package core.cash.application;

import core.book.domain.Book;
import core.book.domain.Jenre;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.cash.infrastructure.CashRepository;
import core.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;



class CashLedgerTest {
    CashLedger cashLedger;

    @Mock
    CashRepository cashRepository;

    User user;

    int userNum = 1;
    int bookSerialNum = 1;
    int cash = 10000;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cashLedger = new CashLedger(cashRepository);

        user = new User("이영한");
    }


    @Test
    public void writeOutputCashTransaction() {

        Book book = new Book(1, 1, "드래곤라자", Jenre.FANTASY, 300);

        cashLedger.writeOutput(new CashTransaction(userNum, bookSerialNum, CashTransactionType.OUTPUT));

        verify(cashRepository).write(new CashTransaction(userNum, book.getSerialNum(), CashTransactionType.INPUT));
    }

    @Test
    public void writeInputCashTransaction() {
        cashLedger.writeInput(new CashTransaction(userNum, cash, CashTransactionType.INPUT));
        verify(cashRepository).write(new CashTransaction(userNum, cash, CashTransactionType.INPUT));
    }

    @Test
    public void getUserMoney() {
        given(cashRepository.getOwnMoney(user)).willReturn(cash);
        assertThat(cashLedger.getUserMoney(user)).isEqualTo(cash);
    }
}