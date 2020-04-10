package core.cash;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class BookTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookTransactionNum;
    private int bookSerialNum;
    private int userNum;
//    private Date date;
    private TransactionType txType;
    private int cashTransactionNum;

    public BookTransaction() {
    }

    public BookTransaction(int userNum, int bookSerialNum, int cashTransactionNum, TransactionType txType) {
        this.userNum = userNum;
//        this.date = new Date();
        this.txType = txType;
        this.bookSerialNum = bookSerialNum;
        this.cashTransactionNum = cashTransactionNum;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        BookTransaction tx = (BookTransaction) obj;

        if(!super.equals(obj)) {
            return false;
        }

        if (tx.getBookSerialNum() != getBookSerialNum()){
            return false;
        }
        if(tx.getBookTransactionNum() != getBookTransactionNum()) {
            return false;
        }
        if(!tx.getTxType().equals(getTxType())) {
            return false;
        }
        if(tx.getUserNum() != getUserNum()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookTransactionNum, bookSerialNum, userNum, txType, cashTransactionNum);
    }

    public int getBookSerialNum() {
        return bookSerialNum;
    }

    public int getBookTransactionNum() {
        return bookTransactionNum;
    }


    public int getUserNum() {
        return userNum;
    }

//    public Date getDate() {
//        return date;
//    }

    public TransactionType getTxType() {
        return txType;
    }

}
