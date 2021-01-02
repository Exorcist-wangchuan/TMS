package service;

import dao.ToolEntityDAO;
import po.PurchaseRecord;
import po.ToolEntity;

public class ToolEntityService {
    private ToolEntityDAO toolEntityDAO=null;

    public void setToolEntityDAO(ToolEntityDAO toolEntityDAO) {
        this.toolEntityDAO = toolEntityDAO;
    }

    //插入toolentity记录
    public boolean saveToolEntity(ToolEntity toolEntity) {
        //调用DAO
        toolEntityDAO.insertToolEntityDAO(toolEntity);

        return true;
    }

    //删除toolentity记录
    public boolean deleteToolEntity(String pk) {
        //调用DAO
        toolEntityDAO.deleteToolEntityDAO(pk);

        return true;
    }
}
