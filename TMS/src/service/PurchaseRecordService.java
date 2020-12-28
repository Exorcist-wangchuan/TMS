package service;

import com.opensymphony.xwork2.ActionContext;
import dao.ApplyDAO;
import dao.PurchaseRecordDAO;
import po.Application;
import po.PurchaseRecord;
import po.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;


public class PurchaseRecordService {
    public boolean savePurchaseRecord(PurchaseRecord purchaseRecord) {

        //调用DAO
        PurchaseRecordDAO purchaseRecordDAO = new PurchaseRecordDAO();
        purchaseRecordDAO.insertPurchaseRecordDAO(purchaseRecord);
        return true;
    }

    public List<PurchaseRecord> getPurchaseRecord() {
        List<PurchaseRecord> list = new ArrayList<>();
        PurchaseRecordDAO purchaseRecordDAO = new PurchaseRecordDAO();
        list = purchaseRecordDAO.searchPurchaseRecordDAO();
        return list;
    }

}

