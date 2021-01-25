package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import po.FixRecord;
import po.ProcessRecord;
import po.PurchaseRecord;
import po.User;
import pojo.CheckList;
import service.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FixRecordAction extends ActionSupport{
    private FixRecord fixRecord;
    private IFixRecordService fixRecordService = null;
    private IProcessService processService = null;
    private CheckList checkList;
    private File upload;
    private String uploadFileName;

    //getter
    public FixRecord getFixRecord() {
        return fixRecord;
    }
    public CheckList getCheckList() {
        return checkList;
    }

    //setter
    public void setUpload(File upload){
        this.upload = upload;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
    public void setFixRecord(FixRecord fixRecord) {
        this.fixRecord = fixRecord;
    }
    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }
    public void setFixRecordService(IFixRecordService fixRecordService) {
        this.fixRecordService = fixRecordService;
    }
    public void setProcessService(IProcessService processService) {
        this.processService = processService;
    }

    //初级员工
    public String dealFixApply() throws IOException {

        //获取时间构造eid
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat eidFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String eid = eidFormat.format(new Date());
        String applyDate = sdf.format(new Date());

        //需要先保存ProcessRecord(主键)
        //构造Process
        ProcessRecord process = new ProcessRecord();
        process.seteID(eid);
        process.setDname("fix");
        process.setFinish(1);
        User user= (User)ActionContext.getContext().getSession().get("user");
        int applyUID=user.getId();
        System.out.println(applyUID);
        process.setApply_UID(applyUID);
        process.setApply_Date(applyDate);
        //调用service
        boolean firstRes = processService.saveProcess(process);

        //保存图片
        String path="D:\\img\\";
        path = path+fixRecord.getSeqID()+"\\";
        File file = new File(path);
        if (!file.exists()) file.mkdir();
        FileUtils.copyFile(upload, new File(file,uploadFileName));
        fixRecord.setImg(path+uploadFileName);
        String file2 = new File(file,uploadFileName).toString();
        System.out.println("文件名："+uploadFileName);
        System.out.println("file2:"+file2);

        //保存报修记录
        fixRecord.seteID(eid);
        fixRecord.setApplyUID(applyUID);
        boolean secondRes = fixRecordService.saveFixRecord(fixRecord);
        //判断
        if (firstRes && secondRes){
            return "success";
        }
        else{
            this.addActionError("报修记录提交失败，请重新填写！");
            return "fail";
        }
    }

    //高级员工
    //高级员工获取报修记录
    public String getStorageFixRecord() {
        List<FixRecord> list = new ArrayList<>();
        list = fixRecordService.getFixRecord();
        ActionContext.getContext().getSession().put("fixRecords", list);
        return "success";
    }

    //高级员工审核通过
    public String reviewFixRecord(){
        List<String> passedList = checkList.getCheckList();
        System.out.println(passedList.get(0));
        boolean res = processService.fix_finalCheck(passedList);
        if (res) return "success";
        else return "fail";
    }

}
