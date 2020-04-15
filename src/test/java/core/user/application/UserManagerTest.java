package core.user.application;

import core.user.domain.User;
import core.user.infrastructure.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;



class UserManagerTest {
    UserManager userManager;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userManager = new UserManager(userRepository);
    }

    @Test
    @DisplayName("모든 유저 정보를 가져온다")
    public void getAllUser(){
        List<User> users = List.of(new User("이영한"), new User("이영규"));
        given(userManager.getAllUsers()).willReturn(users);
        assertThat(userManager.getAllUsers()).isEqualTo(users);

    }

    @Test
    public void getUserByName() {
        User user = new User(1, "이영한");
        given(userRepository.getUserByName("이영한")).willReturn(Optional.of(user));

        assertThat(userManager.getUserByName(user.getName())).isEqualTo(user);
    }

}