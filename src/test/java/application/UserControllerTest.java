package application;

import core.user.application.UserManager;
import core.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


class UserControllerTest {
    UserController userController;

    @Mock
    UserManager userManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userManager);
    }

    @Test
    public void getAllUsers() {
        List<User> users = List.of(new User(1, "이영한"),
                new User(2, "이영규"));
        given(userManager.getAllUsers()).willReturn(users);
        assertThat(userController.getAllUsers()).isEqualTo(users);

    }

    @Test
    public void getUserMoney() {

    }


}