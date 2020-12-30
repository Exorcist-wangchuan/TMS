package service;

import com.opensymphony.xwork2.ActionContext;
import dao.ProcessDAO;
import po.ProcessRecord;
import po.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProcessService {
    private ProcessDAO processDAO=null;

    public void setProcessDAO(ProcessDAO processDAO) {
        this.processDAO = processDAO;
    }

    public boolean saveProcess(ProcessRecord process) {
        //调用DAO
        processDAO.insertProcess(process);
        return true;
    }

    /*
    * 以下为采购入库流程专用
    * 包含监管员初审、经理终审
    * */
    //监管员通过初审
    public boolean purchase_firstCheck(List<String> passedList){
        //获取日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        //获取监管员ID
        User sv = (User) ActionContext.getContext().getSession().get("user");
        int id = sv.getId();

        for (String seqID:passedList){
            ProcessRecord processRecord = processDAO.getProcessBySeqID(Integer.parseInt(seqID));
            if (processRecord!=null){
                processRecord.setFirst_Check_UID(id);
                processRecord.setFirst_Check_Date(date);
                processRecord.setFinish(2);
                processDAO.updateProcess(processRecord);
            }else {
                //意味着没有流程记录，出错
            }
        }
        return true;
    }

}
