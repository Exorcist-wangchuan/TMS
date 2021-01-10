package dao;

import po.User;

import java.util.List;

public interface IUserDAO extends IBaseDAO {
    List findByHql(String hql);

    User getUserByID(int id);

    //系统管理员获取用户列表
    List<User> searchManageDAO();

    //删除用户
    void delete(User user);

    //修改用户权限
    void update(User user);

    //增加用户
    void insert(User user);
}
