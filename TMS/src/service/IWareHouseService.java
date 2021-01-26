package service;

import dao.IWareHouseDAO;
import dao.WareHouseDAO;
import po.WareHouseRecord;

import java.util.List;

public interface IWareHouseService {
    public WareHouseRecord searchWareHouse(WareHouseRecord wareHouseRecord);

    IWareHouseDAO getWareHouseDAO();

    void setWareHouseDAO(IWareHouseDAO wareHouseDAO);

    boolean saveWareHouse(WareHouseRecord wareHouseRecord);

    List<WareHouseRecord> getWareHouseRecord();

    boolean ChangeYN(List<String> passedList);

    public boolean INYN(WareHouseRecord wareHouseRecord);
}
