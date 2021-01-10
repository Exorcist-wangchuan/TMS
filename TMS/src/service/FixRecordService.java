package service;

import dao.FixRecordDAO;
import dao.IFixRecordDAO;
import dao.PurchaseRecordDAO;
import po.FixRecord;
import po.PurchaseRecord;

import java.util.ArrayList;
import java.util.List;

public class FixRecordService implements IFixRecordService {
    private IFixRecordDAO fixRecordDAO=null;

    public void setFixRecordDAO(IFixRecordDAO fixRecordDAO) {
        this.fixRecordDAO = fixRecordDAO;
    }

    @Override
    public boolean saveFixRecord(FixRecord fixRecord) {
        //调用DAO
        fixRecordDAO.insertFixRecordDAO(fixRecord);

        return true;
    }

    //高级用户获取报修记录
    @Override
    public List<FixRecord> getFixRecord() {
        List<FixRecord> list = new ArrayList<>();
        list = fixRecordDAO.searchFixRecordDAO();
        return list;
    }
}
