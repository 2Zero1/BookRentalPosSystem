//package domain;
//
//import core.cash.application.CashLedger;
//import core.cash.infrastructure.CashRepository;
//import core.cash.domain.CashTransaction;
//import core.cash.domain.CashTransactionType;
//import core.user.domain.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.BDDMockito.given;
//
//class CashLedgerTest {
//    CashLedger cashLedger;
//
//    @Mock
//    CashRepository cashRepository;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        cashLedger = new CashLedger(cashRepository);
//    }
//
//    @Test
//    public void hasEnoughMoney() {
//        User user = new User("이영한");
//        given(cashRepository.getOwnMoney(user)).willReturn(500);
//        boolean rentResult = cashLedger.hasEnoughMoney(user,400);
//
//        assertThat(rentResult).isEqualTo(true);
//    }
//
//    @Test
//    public void hasNotEnoughMoney() {
//        User user = new User("이영한");
//        given(cashRepository.getOwnMoney(user)).willReturn(300);
//        boolean rentResult = cashLedger.hasEnoughMoney(user,400);
//
//        assertThat(rentResult).isEqualTo(false);
//    }
//
//
//    @Test
//    public void writeInputCashTransaction() {
//        CashTransaction cashTransaction = new CashTransaction(1,1,1, CashTransactionType.INPUT);
//        given(cashRepository.write(cashTransaction)).willReturn(true);
//
//        assertThat(cashLedger.write(cashTransaction)).isEqualTo(true);
//    }
//
//}