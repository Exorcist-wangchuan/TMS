package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import po.Application;
import po.ProcessRecord;
import po.PurchaseRecord;
import service.ApplyService;
import service.ProcessService;
import service.PurchaseRecordService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class PurchaseRecordAction extends ActionSupport {
    private PurchaseRecord purchaseRecord;
    private PurchaseRecordService purchaseRecordService = null;

    private ProcessService processService = null;

    //getter
    public PurchaseRecord getPurchaseRecord() {
        return purchaseRecord;
    }

    //setter
    public void setPurchaseRecord(PurchaseRecord purchaseRecord) {
        this.purchaseRecord = purchaseRecord;
    }

    public void setPurchaseRecordService(PurchaseRecordService purchaseRecordService) {
        this.purchaseRecordService = purchaseRecordService;
    }

    public void setProcessService(ProcessService processService) {
        this.processService = processService;
    }

    public String dealPurchaseApply() {
        boolean firstRes = purchaseRecordService.savePurchaseRecord(purchaseRecord);
        //获取时间构造eid
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

        //构造Process
        ProcessRecord process = new ProcessRecord();
        process.seteID();
        if (firstRes) return "success";
        else return "fail";
    }

    //监管员获取采购记录
    public String getStoragePurchaseRecord() {
        List<PurchaseRecord> list = new ArrayList<>();
        list = purchaseRecordService.getPurchaseRecord();
        ActionContext.getContext().getSession().put("purchaseRecords", list);
        return "success";
    }

    //经理获取采购记录
    public String getStoragePurchaseRecord_Manager() {
        List<PurchaseRecord> list = new ArrayList<>();
        list = purchaseRecordService.getPurchaseRecord_Manager();
        ActionContext.getContext().getSession().put("purchaseRecords_Manager", list);
        return "success";
    }
}
