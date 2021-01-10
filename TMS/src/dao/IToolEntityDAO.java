package dao;

import po.ToolEntity;

public interface IToolEntityDAO extends IBaseDAO {
    //插入记录（采购入库流程）
    void insertToolEntityDAO(ToolEntity toolEntity);

    //删除记录（报废流程）
    void deleteToolEntityDAO(String pk);

    //获得记录
    ToolEntity getToolEntityByCodeandSeqID(String pk);

    //修改记录
    void updateToolEntity(ToolEntity toolEntity);
}
