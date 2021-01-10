package service;

import dao.DefineToolDAO;
import po.DefineTool;

import java.util.List;

public interface IDefineToolService {
    DefineToolDAO getDefineToolDAO();

    void setDefineToolDAO(DefineToolDAO defineToolDAO);

    //查询记录
    DefineTool searchDefineTool(int id);

    //批量获取记录
    List<DefineTool> getDefineTool();

    //插入记录
    boolean saveDefineTool(DefineTool defineTool);

    //修改记录
    boolean updateDefineTool(DefineTool defineTool);

    //删除definetool记录
    boolean deleteDefineTool(List<String> passedList);
}
