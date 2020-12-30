package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import po.ProcessRecord;
import po.PurchaseRecord;
import pojo.CheckList;
import service.ProcessService;
import service.PurchaseRecordService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PurchaseRecordAction extends ActionSupport {
    private PurchaseRecord purchaseRecord;
    private PurchaseRecordService purchaseRecordService = null;
    private ProcessService processService = null;

    private CheckList checkList;

    //getter
    public PurchaseRecord getPurchaseRecord() {
        return purchaseRecord;
    }
    public CheckList getCheckList() {
        return checkList;
    }

    //setter
    public void setPurchaseRecord(PurchaseRecord purchaseRecord) {
        this.purchaseRecord = purchaseRecord;
    }
    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }
    public void setPurchaseRecordService(PurchaseRecordService purchaseRecordService) {
        this.purchaseRecordService = purchaseRecordService;
    }
    public void setProcessService(ProcessService processService) {
        this.processService = processService;
    }

    //高级员工
    public String dealPurchaseApply() {

        //获取时间构造eid
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat eidFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String eid = eidFormat.format(new Date());
        String applyDate = sdf.format(new Date());
        //需要先保存ProcessRecord(主键)
        //构造Process
        ProcessRecord process = new ProcessRecord();
        process.seteID(eid);
        process.setDname("purchase");
        process.setFinish(1);
        process.setApply_UID(purchaseRecord.getApplyUID());
        process.setApply_Date(applyDate);
        //调用service
        boolean firstRes = processService.saveProcess(process);
        //保存采购记录
        purchaseRecord.seteID(eid);
        boolean secondRes = purchaseRecordService.savePurchaseRecord(purchaseRecord);
        //判断
        if (firstRes && secondRes) return "success";
        else return "fail";
    }


    //监管员
    //监管员获取采购记录
    public String getStoragePurchaseRecord() {
        List<PurchaseRecord> list = new ArrayList<>();
        list = purchaseRecordService.getPurchaseRecord();
        ActionContext.getContext().getSession().put("purchaseRecords", list);
        return "success";
    }

    //监管员审核通过
    public String reviewPurchaseRecord(){
        List<String> passedList = checkList.getCheckList();
        boolean res = processService.purchase_firstCheck(passedList);

        if (res) return "success";
        else return "fail";
    }


    //经理
    //经理获取采购记录
    public String getStoragePurchaseRecord_Manager() {
        List<PurchaseRecord> list = new ArrayList<>();
        list = purchaseRecordService.getPurchaseRecord_Manager();
        ActionContext.getContext().getSession().put("purchaseRecords_Manager", list);
        return "success";
    }
}
