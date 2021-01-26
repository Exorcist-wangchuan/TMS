package service;

import dao.IToolEntityDAO;
import dao.ToolEntityDAO;
import po.ToolEntity;

public interface IToolEntityService {
    void setToolEntityDAO(IToolEntityDAO toolEntityDAO);

    //插入toolentity记录
    boolean saveToolEntity(ToolEntity toolEntity);

    //删除toolentity记录
    boolean deleteToolEntity(String pk);

    //修改toolentity记录
    boolean updateToolEntity(ToolEntity toolEntity);

    //查询
    ToolEntity getToolEntity(String pk);
}
