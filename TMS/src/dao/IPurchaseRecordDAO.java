package dao;

import po.PurchaseRecord;

import java.util.List;

public interface IPurchaseRecordDAO extends IBaseDAO {
    void insertPurchaseRecordDAO(PurchaseRecord purchaseRecord);

    //通过code seqid查询
    PurchaseRecord getPurchaseRecordByCodeandSeqID(String pk);

    //监管员获取采购记录
    List<PurchaseRecord> searchPurchaseRecordDAO();

    //经理获取采购记录
    List<PurchaseRecord> searchPurchaseRecordDAO_Manager();
}
