package core.user;

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

    public User(String name) {
        this.name = name;
    }
    public User(int userNum, String name) {
        this.userNum = userNum;
        this.name = name;
    }


    public int getUserNum() {
        return userNum;
    }

    public String getName() {
        return name;
    }
}
