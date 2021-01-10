package service;

import dao.ScrapDAO;
import po.Scrap;

import java.util.List;

public interface IScrapRecordService {
    ScrapDAO getScrapDAO();

    void setScrapDAO(ScrapDAO scrapDAO);

    boolean saveScrap(Scrap scrap);

    //监管员获取报废记录
    List<Scrap> getScrapRecord();

    //经理获取报废记录
    List<Scrap> getScrapRecord_Manager();

    //指定seqid查询
    Scrap getScrapRecordByCodeandId(String pk);
}
