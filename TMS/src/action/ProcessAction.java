package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.Parameter;
import po.ProcessRecord;
import po.PurchaseRecord;
import service.ProcessService;

public class ProcessAction extends ActionSupport {

    String timelineByeID;
    private ProcessService processService = null;

    public String getTimelineByeID() {
        return timelineByeID;
    }

    public void setTimelineByeID(String timelineByeID) {
        this.timelineByeID = timelineByeID;
    }

    public ProcessService getProcessService() {
        return processService;
    }

    public void setProcessService(ProcessService processService) {
        this.processService = processService;
    }

    //指定eid查询process
    public String getProcessByeID() {
        ProcessRecord processRecord=new ProcessRecord();
        processRecord=processService.getProcessByeId(timelineByeID);
        System.out.println("action eid:"+processRecord.geteID());
        if(processRecord.getDname()!=null){
            System.out.println("dname"+processRecord.getDname());
        }
        if(processRecord.geteID().equals("null")){
            return "fail";
        }
        ActionContext.getContext().getSession().put("processSearchResult", processRecord);
        return "success";
    }
}

