package core.book.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class BookTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookTransactionNum;
    private int bookSerialNum;
    private int userNum;
    private Date date;
    private BookTransactionType txType;
    private int cashTransactionNum;

    public BookTransaction() {
    }

    public BookTransaction(int userNum, int bookSerialNum, int cashTransactionNum, BookTransactionType txType) {
        this.userNum = userNum;
        this.date = new Date();
        this.txType = txType;
        this.bookSerialNum = bookSerialNum;
        this.cashTransactionNum = cashTransactionNum;
    }
    public BookTransaction(int userNum, int bookSerialNum,  BookTransactionType txType) {
        this.userNum = userNum;
        this.date = new Date();
        this.txType = txType;
        this.bookSerialNum = bookSerialNum;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        BookTransaction tx = (BookTransaction) obj;

        return Objects.equals(tx.getBookTransactionNum(),getBookTransactionNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookTransactionNum);
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

    public BookTransactionType getTxType() {
        return txType;
    }

    public int getCashTransactionNum() {
        return cashTransactionNum;
    }
}
