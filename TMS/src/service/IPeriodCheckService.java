package service;

import dao.DefineToolDAO;
import dao.PeriodCheckDAO;
import dao.UserDAO;
import po.PeriodCheck;
import util.JavaMailUtil;

import java.util.List;
import java.util.Map;

public interface IPeriodCheckService {

    void setMailUtil(JavaMailUtil mailUtil);

    String insertPeriodCheckByList(List<String> passedList);

    //分析工夹具维护时间是否到达
    Map<Integer, PeriodCheck> toolAnalyze();

    void alert();
}
