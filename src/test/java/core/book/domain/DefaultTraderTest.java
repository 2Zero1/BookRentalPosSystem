package core.book.domain;

import core.book.application.BookRentLedger;
import core.cash.application.CashLedger;
import core.cash.domain.CashTransaction;
import core.cash.domain.CashTransactionType;
import core.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class DefaultTraderTest {
    DefaultTrader defaultTrader;
    @Mock
    CashLedger cashLedger;

    @Mock
    BookRentLedger bookRentLedger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        defaultTrader = new DefaultTrader(cashLedger, bookRentLedger);
    }

    @Test
    public void trade() {
        User user = new User(1,"이영한");
        Book book = new Book(1,1,"드래곤라자",Jenre.FANTASY, 300);
        CashTransaction cashTransaction = new CashTransaction(1);
        given(cashLedger.write(user,book, CashTransactionType.OUTPUT)).willReturn(cashTransaction);
        defaultTrader.trade(user,book);
        verify(bookRentLedger).write(user.getUserNum(),book.getSerialNum(),cashTransaction.getCashTransactionNum(), BookTransactionType.RENT);

    }

}