package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import po.Application;
import po.PurchaseRecord;
import service.ApplyService;
import service.PurchaseRecordService;

import java.util.ArrayList;
import java.util.List;


public class PurchaseRecordAction extends ActionSupport {
    private PurchaseRecord purchaseRecord;
    private PurchaseRecordService purchaseRecordService = null;

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

    public String dealPurchaseApply() {
        boolean res = purchaseRecordService.savePurchaseRecord(purchaseRecord);
        if (res) return "success";
        else return "fail";
    }

    public String getStoragePurchaseRecord() {
        List<PurchaseRecord> list = new ArrayList<>();
        list = purchaseRecordService.getPurchaseRecord();
        ActionContext.getContext().getSession().put("purchaseRecords", list);
        return "success";
    }

}
