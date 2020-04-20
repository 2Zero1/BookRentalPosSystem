package core.cash.domain.exception;

public class CashNotEnoughException extends CashEntityException {
    public CashNotEnoughException(int userCash, int bookPrice) {
        super("잔액이 충분하지 않습니다.(대여비 : %d, 비용 : %d)", bookPrice, userCash);
    }
}
