package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.DefineToolDAO;
import po.DefineTool;
import po.Scrap;
import pojo.CheckList;
import service.DefineToolService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefineToolAction extends ActionSupport {
    private DefineTool defineTool;
    private DefineToolService defineToolService=null;
    private CheckList deletelist;

    public DefineTool getDefineTool() {
        return defineTool;
    }

    public void setDefineTool(DefineTool defineTool) {
        this.defineTool = defineTool;
    }

    public DefineToolService getDefineToolService() {
        return defineToolService;
    }

    public void setDefineToolService(DefineToolService defineToolService) {
        this.defineToolService = defineToolService;
    }

    public CheckList getDeletelist() {
        return deletelist;
    }

    public void setDeletelist(CheckList deletelist) {
        this.deletelist = deletelist;
    }

    //获取记录
    public String getDefintTool_supervisor() {
        List<DefineTool> list = new ArrayList<>();
        list = defineToolService.getDefineTool();
        for (DefineTool df:list){
            System.out.println(df.getId());
        }
        ActionContext.getContext().getSession().put("DefintToolRecords", list);
        return "success";
    }

    //插入记录
    public String dealDefineToolApply(){

        boolean res=defineToolService.saveDefineTool(defineTool);
        if (res) return "success";
        else return "fail";
    }

    //修改definetool记录
    public String updateDefineTool(){
        DefineTool df=defineToolService.searchDefineTool(defineTool.getId());

        //将原记录中的录入人信息赋予新的记录
        defineTool.setRecByName(df.getRecByName());
        defineTool.setRecBy(df.getRecBy());
        defineTool.setRecOn(df.getRecOn());
        boolean res=defineToolService.updateDefineTool(defineTool);
        if(res) return "success";
        return "fail";
    }

    //删除definetool记录
    public String deleteDefineTool(){
        System.out.println("slsjdkj"+deletelist.getCheckList().size());
        List<String> passedList = deletelist.getCheckList();

        boolean res = defineToolService.deleteDefineTool(passedList);
        if (res) return "success";
        else return "fail";
    }
}
