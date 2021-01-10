package dao;

import po.PeriodCheck;

import java.util.List;

public interface IPeriodCheckDAO extends IBaseDAO {
    List getPeriodCheckList();

    void insertPeriodCheck(PeriodCheck periodCheck);

    int getPMPeriod(String code);
}
