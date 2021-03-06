package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import po.*;
import pojo.CheckList;
import service.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PurchaseRecordAction extends ActionSupport {
    private PurchaseRecord purchaseRecord;
    private ToolEntity toolEntity;

    private IPurchaseRecordService purchaseRecordService = null;
    private IProcessService processService = null;
    private IToolEntityService toolEntityService=null;
    private IPeriodCheckService periodCheckService=null;

    private CheckList checkList;
    private File upload;
    private String uploadFileName;
    //工夹具类别列表
    private List codeList;

    public List getCodeList() {
        return codeList;
    }

    public void setCodeList(List codeList) {
        this.codeList = codeList;
    }

    public void setUpload(File upload){
        this.upload = upload;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
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

    public void setPurchaseRecordService(IPurchaseRecordService purchaseRecordService) {
        this.purchaseRecordService = purchaseRecordService;
    }

    public void setProcessService(IProcessService processService) {
        this.processService = processService;
    }

    public void setToolEntityService(IToolEntityService toolEntityService) {
        this.toolEntityService = toolEntityService;
    }
    public void setPeriodCheckService(IPeriodCheckService periodCheckService){
        this.periodCheckService = periodCheckService;
    }

    //高级员工获取类别
    public String getToolCode(){
        codeList = purchaseRecordService.getToolCode();
        return "success";
    }

    //高级员工
    public String dealPurchaseApply() throws IOException {
        ActionContext ctx = ActionContext.getContext();
        //获取时间构造eid
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat eidFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String eid = eidFormat.format(new Date());
        String applyDate = sdf.format(new Date());

        //保存图片
        String path="D:\\img\\";
        String judge=
        path = path+purchaseRecord.getCode_seqid().getCode()+"-"+purchaseRecord.getCode_seqid().getSeqID()+"\\";
        File file = new File(path);
        if (!file.exists()) file.mkdir();
        FileUtils.copyFile(upload, new File(file,uploadFileName));
        purchaseRecord.setImg(path+uploadFileName);
        User user=(User)ActionContext.getContext().getSession().get("user");
        int uid=user.getId();
        purchaseRecord.setApplyUID(uid);
        purchaseRecord.setPurchaseDate(applyDate);

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
        tpk.setCode(purchaseRecord.getCode_seqid().getCode());
        tpk.setSeqID(purchaseRecord.getCode_seqid().getSeqID());
        toolEntity.setCode_seqid(tpk);
        toolEntity.setBillNo(purchaseRecord.getBillNo());
        toolEntity.setRegDate(purchaseRecord.getPurchaseDate());
        toolEntity.setUsedCount(0);

        //调用service
        boolean firstRes = processService.saveProcess(process);
        boolean entityRes=toolEntityService.saveToolEntity(toolEntity);

        //保存采购记录
        purchaseRecord.seteID(eid);
        boolean secondRes = purchaseRecordService.savePurchaseRecord(purchaseRecord);

        //判断
        if (firstRes && secondRes && entityRes) {
            System.out.println("采购入库提交成功");
            this.addActionError("采购入库记录提交成功");
            ctx.put("PurchaseApplyResult","采购记录提交成功");
            return "success";
        }
        else {
            System.out.println("采购入库提交失败");
            this.addActionError("采购入库记录提交失败");
            ctx.put("PurchaseApplyResult","采购记录提交失败");
            return "fail";
        }
    }

    //监管员
    //监管员获取采购记录
    public String getStoragePurchaseRecord() {
        System.out.println("test");
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

    //监管员初审不通过
    public String rejectPurchaseRecord(){
        List<String> passedList = checkList.getCheckList();
        boolean res = processService.purchase_firstCheck_reject(passedList);
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
        String re=periodCheckService.insertPeriodCheckByList(passedList);
        if(re.equals("fail"))
            return "fail";
        boolean res = processService.purchase_finalCheck(passedList);
        if (res) return "success";
        else return "fail";
    }

    //经理审核不通过
    public String reviewPurchaseRecord_Manager_reject(){
        List<String> passedList = checkList.getCheckList();
        boolean res = processService.purchase_finalCheck_reject(passedList);
        if (res) return "success";
        else return "fail";
    }

}
