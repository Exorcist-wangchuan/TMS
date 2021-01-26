package service;

import dao.IToolEntityDAO;
import dao.ToolEntityDAO;
import po.PurchaseRecord;
import po.ToolEntity;
import po.ToolEntityPrimaryKey;

public class ToolEntityService implements IToolEntityService {
    private IToolEntityDAO toolEntityDAO=null;

    @Override
    public void setToolEntityDAO(IToolEntityDAO toolEntityDAO) {
        this.toolEntityDAO = toolEntityDAO;
    }

    //插入toolentity记录
    @Override
    public boolean saveToolEntity(ToolEntity toolEntity) {
        //调用DAO
        toolEntityDAO.insertToolEntityDAO(toolEntity);

        return true;
    }

    //删除toolentity记录
    @Override
    public boolean deleteToolEntity(String pk) {
        //调用DAO
        toolEntityDAO.deleteToolEntityDAO(pk);

        return true;
    }



    //修改toolentity记录
    @Override
    public boolean updateToolEntity(ToolEntity toolEntity){
        toolEntityDAO.updateToolEntity(toolEntity);
        return true;

    }

    //查询
    @Override
    public ToolEntity getToolEntity(String pk){
        ToolEntity res=toolEntityDAO.getToolEntityByCodeandSeqID(pk);
        return res;

    }
}
