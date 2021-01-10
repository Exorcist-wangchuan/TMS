package dao;

import po.WareHouseRecord;

import java.util.List;

public interface IWareHouseDAO extends IBaseDAO {
    //插入记录（入库流程）
    void insertWareHouseDAO(WareHouseRecord warehouseRecord);

    List<WareHouseRecord> getAllWareHouse();

    WareHouseRecord getWareHouseRecordByCodeandSeqID(String pk);

    //修改记录
    void updateWareHouseRecord(WareHouseRecord wareHouseRecord);
}
