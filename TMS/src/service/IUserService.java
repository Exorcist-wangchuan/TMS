package service;

import dao.UserDAO;
import po.User;

import java.util.List;

public interface IUserService {

    boolean login(User loginUser);

    //系统管理员获取用户列表
    List<User> getUsers();

    //删除用户
    boolean deleteUsers(List<String> passedList);

    //修改用户权限
    boolean updateUser(User user);

    //增加用户
    boolean addUser(User user);
}
