package service;

import dao.IPeriodCheckDAO;
import dao.IProcessDAO;
import dao.PeriodCheckDAO;
import dao.ProcessDAO;
import po.ProcessRecord;

import java.util.List;

public interface IProcessService {
    void setProcessDAO(IProcessDAO processDAO);

    void setPeriodCheckDAO(IPeriodCheckDAO periodCheckDAO);

    boolean saveProcess(ProcessRecord process);

    //指定codeseqid查询
    ProcessRecord getProcessByeId(String eid);

    /*
     * 以下为采购入库流程专用
     * 包含监管员初审、经理终审
     * */
    //监管员通过初审
    boolean purchase_firstCheck(List<String> passedList);

    //经理终审
    boolean purchase_finalCheck(List<String> passedList);

    /*
     * 以下为报废流程专用
     * 包含监管员初审、经理终审
     * */
    //监管员通过初审
    boolean scrap_firstCheck(List<String> passedList);

    //经理终审
    boolean scrap_finalCheck(List<String> passedList);

    /*以下为报修专用
        包含高级用户终审*/
    //高级员工终审
    boolean fix_finalCheck(List<String> passedList);
}
