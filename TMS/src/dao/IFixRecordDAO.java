package dao;

import po.FixRecord;

import java.util.List;

public interface IFixRecordDAO extends IBaseDAO {
    void insertFixRecordDAO(FixRecord fixRecord);

    //高级用户获取报修记录
    List<FixRecord> searchFixRecordDAO();
}
