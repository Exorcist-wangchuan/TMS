package service;

import dao.ProcessDAO;
import po.ProcessRecord;

public class ProcessService {
    public boolean saveProcess(ProcessRecord process) {
        //调用DAO
        ProcessDAO processDAO = new ProcessDAO();
        processDAO.insertProcess(process);
        return true;
    }
}
