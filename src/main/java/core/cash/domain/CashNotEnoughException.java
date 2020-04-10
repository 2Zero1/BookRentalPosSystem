package core.cash.domain;

public class CashNotEnoughException extends CashEntityException {
    public CashNotEnoughException(int cash) {
        super("잔액이 충분하지 않습니다.(대여비 :%d)", cash);
    }
}
