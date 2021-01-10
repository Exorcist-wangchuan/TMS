package dao;

import po.DefineTool;

import java.util.List;

public interface IDefineToolDAO extends IBaseDAO {
    //搜索记录
    DefineTool searchDefineTool(int id);

    //插入记录
    void insertDefineToolDAO(DefineTool defineTool);

    //更新记录
    void updateDefineTool(DefineTool defineTool);

    //删除记录
    void deleteDefineTool(int id);

    //批量获取记录
    List<DefineTool> searchDefineTool_supervisor();

    //获取类别和责任人的映射
    List<DefineTool> getOwnerAndCode();
}
