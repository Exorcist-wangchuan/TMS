package service;

import dao.WareHouseDAO;
import po.WareHouseRecord;

import java.util.List;

public interface IWareHouseService {
    WareHouseDAO getWareHouseDAO();

    void setWareHouseDAO(WareHouseDAO wareHouseDAO);

    boolean saveWareHouse(WareHouseRecord wareHouseRecord);

    List<WareHouseRecord> getWareHouseRecord();

    boolean ChangeYN(List<String> passedList);
}
