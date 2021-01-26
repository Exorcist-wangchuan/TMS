package dao;

import po.Scrap;

import java.util.List;

public interface IScrapDAO extends IBaseDAO {
    void insertScrap(Scrap scrap);

    //监管员获取报废记录
    List<Scrap> searchScrapRecordDAO();

    //经理获取报废记录
    List<Scrap> searchScrapRecordDAO_Manager();

    //通过code seqid查询
    Scrap getScrapRecordByCodeandSeqID(String pk);

    //获取所有报废记录
    List<Scrap> getAllRecord();
}
