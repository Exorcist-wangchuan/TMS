package action;

import com.opensymphony.xwork2.ActionSupport;
import po.User;
import service.UserService;

public class UserAction extends ActionSupport {
    private User loginUser;
    private UserService userService;

    public User getLoginUser() {
        return loginUser;
    }

    //setter
    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String login() {
        if (userService.login(loginUser)) {
            return loginUser.getLevel();
        }
        return "fail";
    }
}
