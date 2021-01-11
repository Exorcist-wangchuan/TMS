package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import po.ProcessRecord;
import po.Scrap;
import po.User;
import pojo.CheckList;
import service.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScrapRecordAction extends ActionSupport {
    private Scrap scrap;
    private IScrapRecordService scrapService=null;
    private IProcessService processService = null;
    private IToolEntityService toolEntityService = null;
    private CheckList checkList;

    public CheckList getCheckList() {
        return checkList;
    }

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    public IScrapRecordService getScrapService() {
        return scrapService;
    }

    public void setScrapService(ScrapRecordService scrapService) {
        this.scrapService = scrapService;
    }

    public IProcessService getProcessService() {
        return processService;
    }

    public void setProcessService(ProcessService processService) {
        this.processService = processService;
    }

    public IToolEntityService getToolEntityService() {
        return toolEntityService;
    }

    public void setToolEntityService(ToolEntityService toolEntityService) {
        this.toolEntityService = toolEntityService;
    }

    public Scrap getScrap() {
        return scrap;
    }


    public void setScrap(Scrap scrap) {
        this.scrap = scrap;
    }

    //高级员工
    public String dealScrapApply(){
        //获取时间构造eid
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat eidFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String eid = eidFormat.format(new Date());
        String applyDate = sdf.format(new Date());
        //需要先保存ProcessRecord(主键)
        //构造Process
        ProcessRecord process = new ProcessRecord();
        process.seteID(eid);
        process.setDname("scrap");
        process.setFinish(1);
        User sv = (User) ActionContext.getContext().getSession().get("user");
        int id = sv.getId();
        process.setApply_UID(id);
        process.setApply_Date(applyDate);
        //调用service
        boolean firstRes = processService.saveProcess(process);
        //保存报废记录
        scrap.seteID(eid);
        System.out.println(scrap.getCode_seqid().getSeqID());
        boolean secondRes = scrapService.saveScrap(scrap);
        //判断
        if (firstRes && secondRes) return "success";
        else return "fail";
    }

    //监管员
    //监管员获取报废记录
    public String getStorageScrapRecord() {
        List<Scrap> list = new ArrayList<>();
        list = scrapService.getScrapRecord();
        ActionContext.getContext().getSession().put("scrapRecords", list);
        return "success";
    }

    //监管员审核通过
    public String reviewScrapRecord(){
        List<String> passedList = checkList.getCheckList();
        for (String str:passedList){
            System.out.println("ss"+str);
        }
        boolean res = processService.scrap_firstCheck(passedList);
        if (res) return "success";
        else return "fail";
    }

    //经理
    //经理获取报废记录
    public String getStorageScrapRecord_Manager() {
        List<Scrap> list = new ArrayList<>();
        list = scrapService.getScrapRecord_Manager();
        for (Scrap scrap:list){
            System.out.println(scrap.geteID());
        }
        ActionContext.getContext().getSession().put("scrapRecords", list);
        return "success";
    }
    //经理审核通过
    public String reviewPurchaseRecord_Manager(){
        List<String> passedList = checkList.getCheckList();
        for (String str:passedList){
            System.out.println("ss"+str);
            //在entity表中删除对应的工夹具实体记录
            boolean toolres=toolEntityService.deleteToolEntity(str);
        }
        boolean res = processService.scrap_finalCheck(passedList);
        if (res) return "success";
        else return "fail";
    }
}
