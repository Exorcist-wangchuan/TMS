package dao;

import po.ProcessRecord;

public interface IProcessDAO extends IBaseDAO {
    void insertProcess(ProcessRecord process);

    void updateProcess(ProcessRecord processRecord);

    //search
    ProcessRecord getProcessRecord(String eid);

    //to do
    ProcessRecord getPurchaseProcessByCodeandSeqID(String pk);

    //Scrap process to do
    ProcessRecord getScrapProcessByCodeandSeqID(String pk);

    //查询报修表和流程表
    ProcessRecord getFixProcessByCodeandSeqID(String eID);
}
