package service;

import com.opensymphony.xwork2.ActionContext;
import dao.DefineToolDAO;
import po.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefineToolService {
    private DefineToolDAO defineToolDAO=null;

    public DefineToolDAO getDefineToolDAO() {
        return defineToolDAO;
    }

    public void setDefineToolDAO(DefineToolDAO defineToolDAO) {
        this.defineToolDAO = defineToolDAO;
    }

    //查询记录
    public DefineTool searchDefineTool(int id){
        DefineTool defineTool =new DefineTool();
        defineTool=defineToolDAO.searchDefineTool(id);
        return defineTool;
    }

    //批量获取记录
    public List<DefineTool> getDefineTool() {
        List<DefineTool> list = new ArrayList<>();
        list = defineToolDAO.searchDefineTool_supervisor();
        return list;
    }

    //插入记录
    public boolean saveDefineTool(DefineTool defineTool) {
        //录入时间
        SimpleDateFormat eidFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String eid = eidFormat.format(new Date());

        //获取录入人ID
        User sv = (User) ActionContext.getContext().getSession().get("user");
        int id = sv.getId();
        String name=sv.getName();

        defineTool.setRecOn(eid);//设置录入时间
        defineTool.setRecBy(id);//设置录入人编号
        defineTool.setRecByName(name);//设置录入人姓名
        //调用DAO
        defineToolDAO.insertDefineToolDAO(defineTool);
        return true;
    }

    //修改记录
    public boolean updateDefineTool(DefineTool defineTool){
        //修改时间
        SimpleDateFormat eidFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String eid = eidFormat.format(new Date());

        //获取修改人ID
        User sv = (User) ActionContext.getContext().getSession().get("user");
        int id = sv.getId();
        String name=sv.getName();

        defineTool.setEditOn(eid);//设置修改时间
        defineTool.setEditBy(id);//设置修改人
        defineTool.setEditByName(name);
        defineToolDAO.updateDefineTool(defineTool);
        return true;
    }

    //删除definetool记录
    public boolean deleteDefineTool(List<String> passedList) {

        for (String id:passedList) {
            int deleteid=Integer.parseInt(id);
            //调用DAO
            defineToolDAO.deleteDefineTool(deleteid);
        }
        return true;
    }
}
