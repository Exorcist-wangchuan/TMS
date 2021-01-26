package service;

import dao.IPurchaseRecordDAO;
import dao.PurchaseRecordDAO;
import po.PurchaseRecord;

import java.util.List;

public interface IPurchaseRecordService {
    void setPurchaseRecordDAO(IPurchaseRecordDAO purchaseRecordDAO);

    //获取所有工夹具类别代码
    List getToolCode();

    //插入记录
    boolean savePurchaseRecord(PurchaseRecord purchaseRecord);

    //指定seqid查询
    PurchaseRecord getPurchaseRecordByCodeandId(String pk);

    //监管员获取采购记录
    List<PurchaseRecord> getPurchaseRecord();

    //经理获取采购记录
    List<PurchaseRecord> getPurchaseRecord_Manager();
}
