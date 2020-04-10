package core.book;

import core.book.Jenre;

import javax.persistence.*;

@Entity
public class Book {
    private int bookID;        //제목이 동일한 책은 BookID가 동일함.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serialNum;     //각각의 책마다 갖는 identifier
    private String name;
    private Jenre jenre;
    private int price;
    private boolean rented = false;

    public Book() {
    }

    public Book(int bookID, int serialNum, String name, Jenre jenre, int price) {
        this.bookID = bookID;
        this.serialNum = serialNum;
        this.name = name;
        this.jenre = jenre;
        this.price = price;
    }

    public int getBookID() {
        return bookID;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public String getName() {
        return name;
    }

    public Jenre getJenre() {
        return jenre;
    }

    public int getPrice() {
        return price;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public boolean isRented() {
        return rented;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("책 번호 : ");
        stringBuilder.append(getBookID());
        stringBuilder.append(", 책 ID : ");
        stringBuilder.append(getSerialNum());
        stringBuilder.append(", 책 이름 : ");
        stringBuilder.append(getName());
        stringBuilder.append(", 장르 : ");
        stringBuilder.append(getJenre());
        stringBuilder.append(", 대여비 : ");
        stringBuilder.append(getPrice());
        stringBuilder.append(", 대여 가능 여부 : ");
        stringBuilder.append(isRented() == false ? "불가" : "가능");
        return stringBuilder.toString();
    }
}
