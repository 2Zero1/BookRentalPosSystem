package core.user.domain;

public class UserNotFoundException extends UserEntityException {
    public UserNotFoundException(String userName) {
        super("유저를 찾을 수 없습니다.(이름 : %s",userName);
    }
}
