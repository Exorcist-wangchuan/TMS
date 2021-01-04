package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.DefineToolDAO;
import po.DefineTool;
import service.DefineToolService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DefineToolAction extends ActionSupport {
    private DefineTool defineTool;
    private DefineToolService defineToolService=null;

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
}
