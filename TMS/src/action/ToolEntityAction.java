package action;

import com.opensymphony.xwork2.ActionSupport;
import po.ToolEntity;
import service.IToolEntityService;
import service.ToolEntityService;

public class ToolEntityAction extends ActionSupport {
    private ToolEntity toolEntity;

    private IToolEntityService toolEntityService=null;

    public ToolEntity getToolEntity() {
        return toolEntity;
    }

    public void setToolEntity(ToolEntity toolEntity) {
        this.toolEntity = toolEntity;
    }

    public IToolEntityService getToolEntityService() {
        return toolEntityService;
    }

    public void setToolEntityService(IToolEntityService toolEntityService) {
        this.toolEntityService = toolEntityService;
    }


    //修改toolentity记录
    public String updateToolEntity(){
        boolean res=toolEntityService.updateToolEntity(toolEntity);
        if(res) return "success";
        return "fail";
    }
}
