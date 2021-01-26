
package service;

import dao.IPurchaseRecordDAO;
import dao.PeriodCheckDAO;
import dao.PurchaseRecordDAO;
import po.PurchaseRecord;

import java.util.ArrayList;
import java.util.List;


public class PurchaseRecordService implements IPurchaseRecordService {
    private IPurchaseRecordDAO purchaseRecordDAO=null;

    @Override
    public void setPurchaseRecordDAO(IPurchaseRecordDAO purchaseRecordDAO) {
        this.purchaseRecordDAO = purchaseRecordDAO;
    }

    //插入记录
    @Override
    public boolean savePurchaseRecord(PurchaseRecord purchaseRecord) {
        //调用DAO
        purchaseRecordDAO.insertPurchaseRecordDAO(purchaseRecord);

        return true;
    }

    //获取所有工夹具类别代码
    public List getToolCode(){
        List list = purchaseRecordDAO.getToolCode();
        return list;
    }

    //指定seqid查询
    @Override
    public PurchaseRecord getPurchaseRecordByCodeandId(String pk){
        System.out.println("service:"+pk);
        PurchaseRecord purchaseRecord =new PurchaseRecord();
        purchaseRecord=purchaseRecordDAO.getPurchaseRecordByCodeandSeqID(pk);
        return purchaseRecord;
    }

    //监管员获取采购记录
    @Override
    public List<PurchaseRecord> getPurchaseRecord() {
        List<PurchaseRecord> list = new ArrayList<>();
        list = purchaseRecordDAO.searchPurchaseRecordDAO();
        return list;
    }

    //经理获取采购记录
    @Override
    public List<PurchaseRecord> getPurchaseRecord_Manager() {
        List<PurchaseRecord> list = new ArrayList<>();
        list = purchaseRecordDAO.searchPurchaseRecordDAO_Manager();
        return list;
    }
}

