package core.user.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNum;
    private String name;
    private int cash;

    public User(String name) {
        this.name = name;
    }
    public User(int userNum, String name) {
        this.userNum = userNum;
        this.name = name;
    }
    public User(int userNum, String name, int cash) {
        this.userNum = userNum;
        this.name = name;
        this.cash = cash;
    }


    public int getUserNum() {
        return userNum;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (this.getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return user.getUserNum() == this.getUserNum();
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getCash() {
        return cash;
    }
}
