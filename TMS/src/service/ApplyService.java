package service;

import com.opensymphony.xwork2.ActionContext;
import dao.ApplyDAO;
import po.Application;
import po.User;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ApplyService {
    public boolean saveApplication(Application apply){
        //加入时间
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        apply.setRecOn(dateformat.format(date));
        //session
        User user = (User) ActionContext.getContext().getSession().get("user");
        apply.setRecBy(user.getId());
        //调用DAO
        ApplyDAO applyDAO = new ApplyDAO();
        applyDAO.insertApply(apply);
        return true;
    }

    public List<Application> getApplication(){
        List<Application> list = new ArrayList<>();
        ApplyDAO applyDAO = new ApplyDAO();
        list = applyDAO.searchApply();
        return list;
    }

}
