package service;

import com.opensymphony.xwork2.ActionContext;
import dao.DefineToolDAO;
import po.DefineTool;
import po.ProcessRecord;
import po.PurchaseRecord;
import po.User;

import java.text.SimpleDateFormat;
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
}
