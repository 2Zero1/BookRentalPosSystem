package core.book.domain;

import core.user.User;

public interface Trader {
    void trade(User user, Book book);
}
