package application;

import core.user.application.UserManager;
import core.user.domain.User;

import java.util.List;

public class UserController {
    UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    public List<User> getAllUsers() {
        return userManager.getAllUsers();
    }
}
