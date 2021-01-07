package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import po.User;
import pojo.CheckList;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserAction extends ActionSupport {
    private User loginUser;
    private UserService userService;
    private CheckList checkList;

    public User getLoginUser() {
        return loginUser;
    }
    public CheckList getCheckList(){return checkList;}

    //setter
    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    public String login() {
        if (userService.login(loginUser)) {
            return loginUser.getLevel();
        }
        return "fail";
    }

    //获取用户列表
    public String getUserList()
    {
        List<User> list = new ArrayList<>();
        list = userService.getUsers();
        ActionContext.getContext().getSession().put("userlists", list);
        return "success";
    }

    //删除用户
    public String deleteUser(){
        List<String> passedList = checkList.getCheckList();
        boolean res = userService.deleteUsers(passedList);
        if (res) return "success";
        else return "fail";
    }

    //修改用户权限
    public String updateUser()
    {
        boolean res=userService.updateUser(loginUser);
        if (res) return "success";
        else return "fail";
    }

    //增加用户
    public String addUser()
    {
        boolean res=userService.addUser(loginUser);
        if (res) return "success";
        else return "fail";
    }


}
