package service;

import dao.ToolEntityDAO;
import po.ToolEntity;

public interface IToolEntityService {
    void setToolEntityDAO(ToolEntityDAO toolEntityDAO);

    //插入toolentity记录
    boolean saveToolEntity(ToolEntity toolEntity);

    //删除toolentity记录
    boolean deleteToolEntity(String pk);

    //修改toolentity记录
    boolean updateToolEntity(ToolEntity toolEntity);
}
