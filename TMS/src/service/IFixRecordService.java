package service;

import dao.FixRecordDAO;
import po.FixRecord;

import java.util.List;

public interface IFixRecordService {
    void setFixRecordDAO(FixRecordDAO fixRecordDAO);

    boolean saveFixRecord(FixRecord fixRecord);

    //高级用户获取报修记录
    List<FixRecord> getFixRecord();
}
