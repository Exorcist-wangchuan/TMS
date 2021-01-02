package action;

import com.opensymphony.xwork2.ActionSupport;
import po.ToolEntity;
import service.ToolEntityService;

public class ToolEntityAction extends ActionSupport {
    private ToolEntity toolEntity;

    private ToolEntityService toolEntityService=null;

    public ToolEntity getToolEntity() {
        return toolEntity;
    }

    public void setToolEntity(ToolEntity toolEntity) {
        this.toolEntity = toolEntity;
    }

    public ToolEntityService getToolEntityService() {
        return toolEntityService;
    }

    public void setToolEntityService(ToolEntityService toolEntityService) {
        this.toolEntityService = toolEntityService;
    }

    //修改toolentity记录
    public String updateToolEntity(){
        boolean res=toolEntityService.updateToolEntity(toolEntity);
        if(res) return "success";
        return "fail";
    }
}
