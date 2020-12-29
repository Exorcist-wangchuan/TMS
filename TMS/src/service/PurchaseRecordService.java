
package service;

import dao.PurchaseRecordDAO;
import po.PurchaseRecord;

import java.util.ArrayList;
import java.util.List;


public class PurchaseRecordService {
    public boolean savePurchaseRecord(PurchaseRecord purchaseRecord) {

        //调用DAO
        PurchaseRecordDAO purchaseRecordDAO = new PurchaseRecordDAO();
        purchaseRecordDAO.insertPurchaseRecordDAO(purchaseRecord);

        return true;
    }

    //监管员获取采购记录
    public List<PurchaseRecord> getPurchaseRecord() {
        List<PurchaseRecord> list = new ArrayList<>();
        PurchaseRecordDAO purchaseRecordDAO = new PurchaseRecordDAO();
        list = purchaseRecordDAO.searchPurchaseRecordDAO();
        return list;
    }

    //经理获取采购记录
    public List<PurchaseRecord> getPurchaseRecord_Manager() {
        List<PurchaseRecord> list = new ArrayList<>();
        PurchaseRecordDAO purchaseRecordDAO = new PurchaseRecordDAO();
        list = purchaseRecordDAO.searchPurchaseRecordDAO_Manager();
        return list;
    }
}

