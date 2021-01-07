package service;
import com.opensymphony.xwork2.ActionContext;
import dao.WareHouseDAO;
import po.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WareHouseService {
    private WareHouseDAO wareHouseDAO=null;

    public WareHouseDAO getWareHouseDAO() {
        return wareHouseDAO;
    }

    public void setWareHouseDAO(WareHouseDAO wareHouseDAO) {
        this.wareHouseDAO = wareHouseDAO;
    }

    public boolean saveWareHouse(WareHouseRecord wareHouseRecord){
        //加入时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        //session
        User user = (User) ActionContext.getContext().getSession().get("user");
        wareHouseRecord.setManager(user.getId());
        wareHouseRecord.setRegDate(date);

        //调用DAO
        wareHouseDAO.insertWareHouseDAO(wareHouseRecord);
        return true;
    }

    public List<WareHouseRecord> getWareHouseRecord() {
        List<WareHouseRecord> list = new ArrayList<>();
        list = wareHouseDAO.getAllWareHouse();
        return list;
    }

    public boolean ChangeYN(List<String> passedList){
        for (String priKey:passedList){
            WareHouseRecord wareHouseRecord = wareHouseDAO.getWareHouseRecordByCodeandSeqID(priKey);
            if (wareHouseRecord!=null){
                wareHouseDAO.updateWareHouseRecord(wareHouseRecord);
            }else {
                //意味着没有流程记录，出错
            }
        }
        return true;

    }

    //监管员获取报废记录
    /*public List<Scrap> getScrapRecord() {
        List<Scrap> list = new ArrayList<>();
        list = scrapDAO.searchScrapRecordDAO();
        return list;
    }*/

    //经理获取报废记录
    /*public List<Scrap> getScrapRecord_Manager() {
        List<Scrap> list = new ArrayList<>();
        list = scrapDAO.searchScrapRecordDAO_Manager();
        return list;
    }*/
}
