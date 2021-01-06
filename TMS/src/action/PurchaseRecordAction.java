package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.Parameter;
import po.*;
import pojo.CheckList;
import service.ProcessService;
import service.PurchaseRecordService;
import service.ToolEntityService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PurchaseRecordAction extends ActionSupport {
    private PurchaseRecord purchaseRecord;
    private ToolEntity toolEntity;

    private PurchaseRecordService purchaseRecordService = null;
    private ProcessService processService = null;
    private ToolEntityService toolEntityService=null;

    private CheckList checkList;
    private List<File> file;

    public void setFile(List<File> file){
        this.file = file;
    }

    //getter
    public PurchaseRecord getPurchaseRecord() {
        return purchaseRecord;
    }

    public CheckList getCheckList() {
        return checkList;
    }

    public ToolEntity getToolEntity() {
        return toolEntity;
    }

    //setter
    public void setPurchaseRecord(PurchaseRecord purchaseRecord) {
        this.purchaseRecord = purchaseRecord;
    }

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    public void setToolEntity(ToolEntity toolEntity) {
        this.toolEntity = toolEntity;
    }

    public void setPurchaseRecordService(PurchaseRecordService purchaseRecordService) {
        this.purchaseRecordService = purchaseRecordService;
    }

    public void setProcessService(ProcessService processService) {
        this.processService = processService;
    }

    public void setToolEntityService(ToolEntityService toolEntityService) {
        this.toolEntityService = toolEntityService;
    }

    //高级员工
    public String dealPurchaseApply() throws IOException {
        //获取时间构造eid
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat eidFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String eid = eidFormat.format(new Date());
        String applyDate = sdf.format(new Date());

        User  user=(User)ActionContext.getContext().getSession().get("user");
        int uid=user.getId();
        purchaseRecord.setApplyUID(uid);

        //需要先保存ProcessRecord(主键)
        //构造Process
        ProcessRecord process = new ProcessRecord();
        process.seteID(eid);
        process.setDname("purchase");
        process.setFinish(1);
        process.setApply_UID(purchaseRecord.getApplyUID());
        process.setApply_Date(applyDate);

        //构造ToolEntity
        ToolEntityPrimaryKey tpk=new ToolEntityPrimaryKey();
        System.out.println(purchaseRecord.getApplyUID());
        System.out.println(purchaseRecord.getCode_seqid().getCode());
        System.out.println(purchaseRecord.getCode_seqid().getSeqID());
        tpk.setCode(purchaseRecord.getCode_seqid().getCode());
        tpk.setSeqID(purchaseRecord.getCode_seqid().getSeqID());
        toolEntity.setCode_seqid(tpk);
        toolEntity.setBillNo(purchaseRecord.getBillNo());
        toolEntity.setRegDate(purchaseRecord.getPurchaseDate());
        toolEntity.setUsedCount(0);
        System.out.println("location"+toolEntity.getLocation());

        //调用service
        boolean firstRes = processService.saveProcess(process);
        boolean entityRes=toolEntityService.saveToolEntity(toolEntity);

        //保存采购记录
        purchaseRecord.seteID(eid);
        boolean secondRes = purchaseRecordService.savePurchaseRecord(purchaseRecord);

        //判断
        if (firstRes && secondRes && entityRes) return "success";
        else return "fail";
    }

//    //指定code seqID查询process和purchaseRecord
//    public String getPurchaseDetail() {
//        Parameter seqID = ActionContext.getContext().getParameters().get("seqID");
//        int id = Integer.parseInt(seqID.toString());
//        PurchaseRecord purchaseRecord = new PurchaseRecord();
//        purchaseRecord = purchaseRecordService.getPurchaseRecordById(id);
//        ProcessRecord process = new ProcessRecord();
//        process = processService.getProcessById(id);
//        ActionContext.getContext().getSession().put("detail_purchaseRecord",purchaseRecord);
//        ActionContext.getContext().getSession().put("detail_processRecord",process);
//        return "json";
//    }

    //监管员
    //监管员获取采购记录
    public String getStoragePurchaseRecord() {
        List<PurchaseRecord> list = new ArrayList<>();
        list = purchaseRecordService.getPurchaseRecord();
        ActionContext.getContext().getSession().put("purchaseRecords", list);
        return "success";
    }

    //监管员审核通过
    public String reviewPurchaseRecord() {
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
        ActionContext.getContext().getSession().put("purchaseRecords", list);
        return "success";
    }

    //经理审核通过
    public String reviewPurchaseRecord_Manager() {
        List<String> passedList = checkList.getCheckList();
        boolean res = processService.purchase_finalCheck(passedList);
        if (res) return "success";
        else return "fail";
    }

}
