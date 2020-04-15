package core.book.domain;

import core.user.domain.User;

public interface Trader {
    void rentBook(User user, int bookSerialNum);

    void returnBook(User userNum, int serialNum);
}
