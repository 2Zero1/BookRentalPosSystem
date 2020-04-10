package core.cash;

public class CashTransactionResult {
    boolean result;
    int transactionNum;
    String failMessage;

    public static CashTransactionResult complete(int transactionNum) {
        return new CashTransactionResult(true, transactionNum);
    }

    public static CashTransactionResult fail(String message) {
        return new CashTransactionResult(false, message);
    }

    public CashTransactionResult(boolean result, int transactionNum) {
        this.result = result;
        this.transactionNum = transactionNum;
    }

    public CashTransactionResult(boolean result, String failMessage) {
        this.result = result;
        this.failMessage = failMessage;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public boolean isResult() {
        return result;
    }

    public int getTransactionNum() {
        return transactionNum;
    }
}
