package core.cash.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class CashTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cashTransactionNum;
    int userNum;
    int bookSerialNum;
    CashTransactionType type;
    Date date;
    int price;

    public CashTransaction() {
    }

    public CashTransaction(int cashTransactionNum) {
        this.cashTransactionNum = cashTransactionNum;
    }

    public CashTransaction(int userNum, int bookSerialNum, int price, CashTransactionType type) {
        this.userNum = userNum;
        this.bookSerialNum = bookSerialNum;
        this.type = type;
        this.date = new Date();
        this.price = price;
    }
    public CashTransaction(int userNum, int price, CashTransactionType type) {
        this.userNum = userNum;
        this.type = type;
        this.date = new Date();
        this.price = price;
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

    public Date getDate() {
        return date;
    }

    public CashTransactionType getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CashTransaction cashTransaction = (CashTransaction) obj;
        return Objects.equals(this.cashTransactionNum, cashTransaction.getCashTransactionNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCashTransactionNum());
    }
}
