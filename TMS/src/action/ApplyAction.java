package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import po.Application;
import service.ApplyService;

import java.util.ArrayList;
import java.util.List;

public class ApplyAction extends ActionSupport {
    private Application apply;
    private ApplyService applyService=null;

    public Application getApply(){
        return apply;
    }
    //setter
    public void setApply(Application apply) {
        this.apply = apply;
    }

    public void setApplyService(ApplyService applyService) {
        this.applyService = applyService;
    }

    public String dealStorageApply(){
        boolean res = applyService.saveApplication(apply);
        if (res) return "success";
        else return "fail";
    }

    public String getStorageApply(){
        List<Application> list = new ArrayList<>();
        list = applyService.getApplication();
        ActionContext.getContext().getSession().put("applications", list);
        return "success";
    }

}
