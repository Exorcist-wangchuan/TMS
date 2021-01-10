package service;

import dao.IScrapDAO;
import dao.ScrapDAO;
import po.Scrap;

import java.util.List;

public interface IScrapRecordService {
    IScrapDAO getScrapDAO();

    void setScrapDAO(IScrapDAO scrapDAO);

    boolean saveScrap(Scrap scrap);

    //监管员获取报废记录
    List<Scrap> getScrapRecord();

    //经理获取报废记录
    List<Scrap> getScrapRecord_Manager();

    //指定seqid查询
    Scrap getScrapRecordByCodeandId(String pk);
}
