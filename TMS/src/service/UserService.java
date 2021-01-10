package service;

import com.opensymphony.xwork2.ActionContext;
import dao.IUserDAO;
import dao.UserDAO;
import org.apache.struts2.interceptor.SessionAware;
import po.User;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    private IUserDAO userDAO=null;

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean login(User loginUser) {
        int id = loginUser.getId();
        String password = loginUser.getPassword();
        String level = loginUser.getLevel();
        String hql = "from User as user where id='" + id + "' and password='" + password + "'"
                + "and level='" + level + "'";
        List<User> list = userDAO.findByHql(hql);
        if (list.isEmpty()) return false;
        else {
            for (User user:list){
                ActionContext.getContext().getSession().put("user", user);
            }
            return true;
        }
    }

    //系统管理员获取用户列表
    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        list = userDAO.searchManageDAO();
        return list;
    }

    //删除用户
    @Override
    public boolean deleteUsers(List<String> passedList){
        for (String ID:passedList){
            User user = userDAO.getUserByID(Integer.parseInt(ID));
            if (user!=null){
                userDAO.delete(user);
            }else {
                //意味着没有用户记录，出错
            }
        }
        return true;
    }

    //修改用户权限
    @Override
    public boolean updateUser(User user)
    {
        userDAO.update(user);
        return true;
    }

    //增加用户
    @Override
    public boolean addUser(User user)
    {
        userDAO.insert(user);
        return true;
    }



}
