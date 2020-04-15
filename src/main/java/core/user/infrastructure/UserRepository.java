package core.user.infrastructure;

import core.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAllUser();

    Optional<User> getUserByName(String name);
}
