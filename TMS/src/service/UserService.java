package service;

import com.opensymphony.xwork2.ActionContext;
import dao.UserDAO;
import org.apache.struts2.interceptor.SessionAware;
import po.User;

import java.util.List;
import java.util.Map;

public class UserService{
    public boolean login(User loginUser) {
        int id = loginUser.getId();
        String password = loginUser.getPassword();
        String level = loginUser.getLevel();
        String hql = "from User as user where id='" + id + "' and password='" + password + "'"
                + "and level='" + level + "'";
        UserDAO dao = new UserDAO();
        List<User> list = dao.findByHql(hql);
        if (list.isEmpty()) return false;
        else {
            for (User user:list){
                ActionContext.getContext().getSession().put("user", user);
            }
            return true;
        }
    }



}
