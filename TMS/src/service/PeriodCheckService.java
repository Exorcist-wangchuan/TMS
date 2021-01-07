package service;

import dao.DefineToolDAO;
import dao.PeriodCheckDAO;
import dao.UserDAO;
import po.DefineTool;
import po.PeriodCheck;
import po.ToolEntityPrimaryKey;
import po.User;
import util.JavaMailUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PeriodCheckService {
    private PeriodCheckDAO periodCheckDAO;
    private DefineToolDAO defineToolDAO;
    private UserDAO userDAO;
    private JavaMailUtil mailUtil;

    public void setPeriodCheckDAO(PeriodCheckDAO periodCheckDAO){
        this.periodCheckDAO = periodCheckDAO;
    }
    public void setDefineToolDAO(DefineToolDAO defineToolDAO){
        this.defineToolDAO = defineToolDAO;
    }
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    public void setMailUtil(JavaMailUtil mailUtil){
        this.mailUtil = mailUtil;
    }

    public void insertPeriodCheckByList(List<String> passedList){
        //获取日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        //生成PeriodCheck
        for(String priKey:passedList){
            //获取code和SeqID
            String[] temp = priKey.split("&");
            String code = temp[0];
            int seqID = Integer.parseInt(temp[1]);
            PeriodCheck periodCheck = new PeriodCheck();
            ToolEntityPrimaryKey pk = new ToolEntityPrimaryKey();
            pk.setCode(code);
            pk.setSeqID(seqID);
            periodCheck.setCode_seqid(pk);
            //加入时间
            periodCheck.setPurchaseDate(date);
            //加入保养周期
            int pmPeriod = periodCheckDAO.getPMPeriod(code);
            periodCheck.setPmPeriod(pmPeriod);
            //加入下次检查时间
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, pmPeriod);
            Date nextCheckDate = calendar.getTime();
            String next = sdf.format(nextCheckDate);
            periodCheck.setNextCheckDate(next);
            //加入报废时间


            //插入记录
            periodCheckDAO.insertPeriodCheck(periodCheck);
        }
    }

    //分析工夹具维护时间是否到达
    public Map<Integer, PeriodCheck> toolAnalyze(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //三天提前通知
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        Date now = calendar.getTime();
        //获取code和责任人的映射关系
        List<DefineTool> defineTools= defineToolDAO.getOwnerAndCode();
        Map<String, Integer> map = new HashMap<>();
        for (DefineTool record:defineTools){
            map.put(record.getCode(), record.getOwner());
        }
        //准备数据结构
        Map<Integer, PeriodCheck> result = new HashMap<>();
        //获取工夹具维护数据
        List<PeriodCheck> list = periodCheckDAO.getPeriodCheckList();
        for (PeriodCheck record: list){
            String nextCheckDate = record.getNextCheckDate();
            Date next = null;
            try {
                next = sdf.parse(nextCheckDate);
            }catch (ParseException pe){
                pe.printStackTrace();
            }
            assert next != null;
            if (next.before(now)){
                result.put(map.get(record.getCode_seqid().getCode()), record);
            }
        }
        return result;
    }

    public void alert(){
        System.out.println("start");
        Map<Integer, PeriodCheck> map = toolAnalyze();
        System.out.println(map.size());
        for (int id:map.keySet()){
            System.out.println(id);
            User user = userDAO.getUserByID(id);
            System.out.println(user.getLevel());
            mailUtil.sendEmail(user.getEmail(), user.getName(), map.get(id));
        }
    }

}