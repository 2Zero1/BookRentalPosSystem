package core.cash;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class CashTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cashTransactionNum;
    int userNum;
    int bookSerialNum;
    int bookTransactionNum;
    CashTransactionType type;
    Date date;

    public CashTransaction() {
    }

    public CashTransaction(int userNum, int bookSerialNum, int bookTransactionNum, CashTransactionType type) {
        this.userNum = userNum;
        this.bookSerialNum = bookSerialNum;
        this.bookTransactionNum = bookTransactionNum;
        this.type = type;
        this.date = new Date();
    }

    public int getCashTransactionNum() {
        return cashTransactionNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public int getBookSerialNum() {
        return bookSerialNum;
    }

    public int getBookTransactionNum() {
        return bookTransactionNum;
    }

    public Date getDate() {
        return date;
    }

    public CashTransactionType getType() {
        return type;
    }

}
