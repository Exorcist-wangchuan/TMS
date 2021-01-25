package service;

import com.opensymphony.xwork2.ActionContext;
import dao.IPeriodCheckDAO;
import dao.IProcessDAO;
import dao.PeriodCheckDAO;
import dao.ProcessDAO;
import po.PeriodCheck;
import po.ProcessRecord;
import po.PurchaseRecord;
import po.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProcessService implements IProcessService {
    private IProcessDAO processDAO=null;
    private IPeriodCheckDAO periodCheckDAO;

    @Override
    public void setProcessDAO(IProcessDAO processDAO) {
        this.processDAO = processDAO;
    }
    @Override
    public void setPeriodCheckDAO(IPeriodCheckDAO periodCheckDAO){
        this.periodCheckDAO = periodCheckDAO;
    }

    @Override
    public boolean saveProcess(ProcessRecord process) {
        //调用DAO
        processDAO.insertProcess(process);
        return true;
    }

    //指定codeseqid查询
    @Override
    public ProcessRecord getProcessByeId(String eid){
        ProcessRecord processRecord=new ProcessRecord();
        processRecord=processDAO.getProcessRecord(eid);
        System.out.println("service eid:"+processRecord.geteID());
        return processRecord;
    }

    /*
    * 以下为采购入库流程专用
    * 包含监管员初审、经理终审
    * */
    //监管员通过初审
    @Override
    public boolean purchase_firstCheck(List<String> passedList){
        //获取日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        //获取监管员ID
        User sv = (User) ActionContext.getContext().getSession().get("user");
        int id = sv.getId();

        for (String priKey:passedList){
            ProcessRecord processRecord = processDAO.getPurchaseProcessByCodeandSeqID(priKey);
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

    //监管员不通过初审
    public boolean purchase_firstCheck_reject(List<String> passedList){
        //获取日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        //获取监管员ID
        User sv = (User) ActionContext.getContext().getSession().get("user");
        int id = sv.getId();
        //修改状态位
        for (String priKey:passedList){
            ProcessRecord processRecord = processDAO.getPurchaseProcessByCodeandSeqID(priKey);
            if (processRecord!=null){
                processRecord.setFirst_Check_UID(id);
                processRecord.setFirst_Check_Date(date);
                processRecord.setFinish(-1);
                processDAO.updateProcess(processRecord);
            }else {
                //意味着没有流程记录，出错
            }
        }
        return true;
    }

    //经理终审
    @Override
    public boolean purchase_finalCheck(List<String> passedList){
        //获取日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        //获取经理ID
        User manager = (User) ActionContext.getContext().getSession().get("user");
        int id = manager.getId();

        for (String priKey:passedList){
            ProcessRecord processRecord = processDAO.getPurchaseProcessByCodeandSeqID(priKey);
            if (processRecord!=null){
                processRecord.setFinal_Check_UID(id);
                processRecord.setFinal_Check_Date(date);
                processRecord.setFinish(3);
                processDAO.updateProcess(processRecord);
            }else {
                //意味着没有流程记录，出错
            }
        }
        return true;
    }

    //经理终审不通过
    public boolean purchase_finalCheck_reject(List<String> passedList){
        //获取日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        //获取经理ID
        User manager = (User) ActionContext.getContext().getSession().get("user");
        int id = manager.getId();

        for (String priKey:passedList){
            ProcessRecord processRecord = processDAO.getPurchaseProcessByCodeandSeqID(priKey);
            if (processRecord!=null){
                processRecord.setFinal_Check_UID(id);
                processRecord.setFinal_Check_Date(date);
                processRecord.setFinish(-1);
                processDAO.updateProcess(processRecord);
            }else {
                //意味着没有流程记录，出错
            }
        }
        return true;
    }

    /*
     * 以下为报废流程专用
     * 包含监管员初审、经理终审
     * */
    //监管员通过初审
    @Override
    public boolean scrap_firstCheck(List<String> passedList){
        //获取日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        //获取监管员ID
        User sv = (User) ActionContext.getContext().getSession().get("user");
        int id = sv.getId();
        for (String pk:passedList){
            //通过code seqID找到对应记录
            ProcessRecord processRecord = processDAO.getScrapProcessByCodeandSeqID(pk);
            System.out.println("pk"+pk);
            if (processRecord!=null){
                processRecord.setFirst_Check_UID(id);
                processRecord.setFirst_Check_Date(date);
                processRecord.setFinish(2);
                processDAO.updateProcess(processRecord);
            }else {
                System.out.println("error:scrap_firstCheck");
                //意味着没有流程记录，出错
            }
        }
        return true;
    }

    //监管员不通过初审
    public boolean scrap_firstCheck_reject(List<String> passedList){
        //获取日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        //获取监管员ID
        User sv = (User) ActionContext.getContext().getSession().get("user");
        int id = sv.getId();
        for (String pk:passedList){
            //通过code seqID找到对应记录
            ProcessRecord processRecord = processDAO.getScrapProcessByCodeandSeqID(pk);
            System.out.println("pk"+pk);
            if (processRecord!=null){
                processRecord.setFirst_Check_UID(id);
                processRecord.setFirst_Check_Date(date);
                processRecord.setFinish(2);
                processDAO.updateProcess(processRecord);
            }else {
                System.out.println("error:scrap_firstCheck");
                //意味着没有流程记录，出错
            }
        }
        return true;
    }

    //经理终审
    @Override
    public boolean scrap_finalCheck(List<String> passedList){
        //获取日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        //获取经理ID
        User manager = (User) ActionContext.getContext().getSession().get("user");
        int id = manager.getId();

        for (String pk:passedList){
            ProcessRecord processRecord = processDAO.getScrapProcessByCodeandSeqID(pk);
            if (processRecord!=null){
                processRecord.setFinal_Check_UID(id);
                processRecord.setFinal_Check_Date(date);
                processRecord.setFinish(3);
                processDAO.updateProcess(processRecord);
            }else {
                System.out.println("error:scrap_finalCheck");
                //意味着没有流程记录，出错
            }
        }
        return true;
    }

    //经理不通过终审
    public boolean scrap_finalCheck_reject(List<String> passedList){
        //获取日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        //获取经理ID
        User manager = (User) ActionContext.getContext().getSession().get("user");
        int id = manager.getId();

        for (String pk:passedList){
            ProcessRecord processRecord = processDAO.getScrapProcessByCodeandSeqID(pk);
            if (processRecord!=null){
                processRecord.setFinal_Check_UID(id);
                processRecord.setFinal_Check_Date(date);
                processRecord.setFinish(-1);
                processDAO.updateProcess(processRecord);
            }else {
                System.out.println("error:scrap_finalCheck");
                //意味着没有流程记录，出错
            }
        }
        return true;
    }

    /*以下为报修专用
    包含高级用户终审*/
    //高级员工终审
    @Override
    public boolean fix_finalCheck(List<String> passedList){
        //获取日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        //获取高级员工ID
        User o2 = (User) ActionContext.getContext().getSession().get("user");
        int id = o2.getId();

        for (String eID:passedList){
            ProcessRecord processRecord = processDAO.getFixProcessByCodeandSeqID(eID);
            if (processRecord!=null){
                processRecord.setFinal_Check_UID(id);
                processRecord.setFinal_Check_Date(date);
                processRecord.setFinish(2);
                processDAO.updateProcess(processRecord);
            }else {
                //意味着没有流程记录，出错
            }
        }
        return true;

    }

}
