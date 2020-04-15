package core.user.application;

import core.user.domain.User;
import core.user.domain.UserNotFoundException;
import core.user.infrastructure.UserRepository;

import java.util.List;

public class UserManager {
    UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUser();
    }

    public User getUserByName(String userName) {
        return userRepository.getUserByName(userName)
                .orElseThrow(() -> new UserNotFoundException(userName));
    }
}
