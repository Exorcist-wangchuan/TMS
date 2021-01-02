
package service;

import dao.PurchaseRecordDAO;
import po.PurchaseRecord;

import java.util.ArrayList;
import java.util.List;


public class PurchaseRecordService {
    private PurchaseRecordDAO purchaseRecordDAO=null;

    public void setPurchaseRecordDAO(PurchaseRecordDAO purchaseRecordDAO) {
        this.purchaseRecordDAO = purchaseRecordDAO;
    }

    public boolean savePurchaseRecord(PurchaseRecord purchaseRecord) {
        //调用DAO
        purchaseRecordDAO.insertPurchaseRecordDAO(purchaseRecord);

        return true;
    }

    //指定seqid查询
    public PurchaseRecord getPurchaseRecordByCodeandId(String pk){
        PurchaseRecord purchaseRecord =new PurchaseRecord();
        purchaseRecord=purchaseRecordDAO.getPurchaseRecordByCodeandSeqID(pk);
        return purchaseRecord;
    }

    //监管员获取采购记录
    public List<PurchaseRecord> getPurchaseRecord() {
        List<PurchaseRecord> list = new ArrayList<>();
        list = purchaseRecordDAO.searchPurchaseRecordDAO();
        return list;
    }

    //经理获取采购记录
    public List<PurchaseRecord> getPurchaseRecord_Manager() {
        List<PurchaseRecord> list = new ArrayList<>();
        list = purchaseRecordDAO.searchPurchaseRecordDAO_Manager();
        return list;
    }
}

