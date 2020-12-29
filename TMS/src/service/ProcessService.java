package service;

import dao.ProcessDAO;
import po.ProcessRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProcessService {
    public boolean saveProcess(ProcessRecord process) {
        //调用DAO
        ProcessDAO processDAO = new ProcessDAO();
        processDAO.insertProcess(process);
        return true;
    }

    //采购入库流程
    //监管员通过初审
    public boolean purchase_firstCheck(List<String> passedList){
        ProcessDAO processDAO = new ProcessDAO();
        //获取日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        for (String SeqID:passedList){
            ProcessRecord processRecord = processDAO.getProcessBySeqID(SeqID);
            //to do
            processRecord.setFirst_Check_UID(0);
            processRecord.setFirst_Check_Date(date);
            //update
            processDAO.updateProcess(processRecord);
        }

        return true;
    }

}
