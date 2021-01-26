package service;

import com.opensymphony.xwork2.ActionContext;
import dao.*;
import po.*;
import util.JavaMailUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WareHouseService implements IWareHouseService {
    private IWareHouseDAO wareHouseDAO = null;
    private IToolEntityDAO toolEntityDAO;
    private IUserDAO userDAO;
    private IScrapRecordService scrapRecordService;
    private JavaMailUtil mailUtil;

    @Override
    public IWareHouseDAO getWareHouseDAO() {
        return wareHouseDAO;
    }

    public void setWareHouseDAO(IWareHouseDAO wareHouseDAO) {
        this.wareHouseDAO = wareHouseDAO;
    }

    public IScrapRecordService getScrapRecordService() {
        return scrapRecordService;
    }

    public void setScrapRecordService(IScrapRecordService scrapRecordService) {
        this.scrapRecordService = scrapRecordService;
    }

    public void setToolEntityDAO(IToolEntityDAO toolEntityDAO) {
        this.toolEntityDAO = toolEntityDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setMailUtil(JavaMailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }

    @Override
    public WareHouseRecord searchWareHouse(WareHouseRecord wareHouseRecord) {
        //查看是否已在数据库中
        String pk = wareHouseRecord.getCode_seqid().getCode().toString() + "&" + wareHouseRecord.getCode_seqid().getSeqID();
        WareHouseRecord res = wareHouseDAO.getWareHouseRecordByCodeandSeqID(pk);
        if (res != null) {
            System.out.println("searchWareHouse success");
            return res;
        }
        res = new WareHouseRecord();
        ToolEntityPrimaryKey nn = new ToolEntityPrimaryKey();
        nn.setSeqID(0);
        nn.setCode("nothing");
        res.setCode_seqid(nn);
        return res;

    }

    @Override
    public boolean saveWareHouse(WareHouseRecord wareHouseRecord) {
        //加入时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        //session
        User user = (User) ActionContext.getContext().getSession().get("user");
        wareHouseRecord.setManager(user.getId());
        wareHouseRecord.setRegDate(date);

        //调用DAO
        wareHouseDAO.insertWareHouseDAO(wareHouseRecord);
        return true;
    }

    @Override
    public List<WareHouseRecord> getWareHouseRecord() {
        List<WareHouseRecord> list = new ArrayList<>();
        list = wareHouseDAO.getAllWareHouse();
        return list;
    }

    @Override
    public boolean ChangeYN(List<String> passedList) {//改为出库状态
        for (String priKey : passedList) {
            WareHouseRecord wareHouseRecord = wareHouseDAO.getWareHouseRecordByCodeandSeqID(priKey);
            if (wareHouseRecord != null) {
                check(wareHouseRecord);
                Date date = new Date();
                wareHouseRecord.setRegDate(date);
                wareHouseDAO.updateWareHouseRecord(wareHouseRecord);
            } else {
                //意味着没有流程记录，出错
            }
        }
        return true;

    }

    @Override
    public boolean INYN(WareHouseRecord wareHouseRecord) {//改为入库状态
        //修改时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        wareHouseRecord.setRegDate(date);

        String priKey = wareHouseRecord.getCode_seqid().getCode().toString() + "&" + wareHouseRecord.getCode_seqid().getSeqID();
        WareHouseRecord w = wareHouseDAO.getWareHouseRecordByCodeandSeqID(priKey);
        if (w != null) {
            wareHouseDAO.INupdateWareHouseRecord(wareHouseRecord);
        } else {
            //意味着没有流程记录，出错
        }
        return true;

    }


    public void check(WareHouseRecord record){
        if (record!=null){
            System.out.println(111);
            double time = scrapRecordService.getAverageScrapTime(record.getCode_seqid().getCode());
            System.out.println("time" + time);
            String pk = record.getCode_seqid().getCode() + "&" + record.getCode_seqid().getSeqID();
            ToolEntity toolEntity = toolEntityDAO.getToolEntityByCodeandSeqID(pk);
            System.out.println(toolEntity.getUsedCount());
            if (toolEntity.getUsedCount()+2 > time){
                int uid = record.getOperator();
                User user = userDAO.getUserByID(uid);
                System.out.println(user.getEmail());
                if (user.getEmail()!=null) {
                    mailUtil.sendScrapEmail(user.getEmail());
                }
            }
        }
    }

    //监管员获取报废记录
    /*public List<Scrap> getScrapRecord() {
        List<Scrap> list = new ArrayList<>();
        list = scrapDAO.searchScrapRecordDAO();
        return list;
    }*/

    //经理获取报废记录
    /*public List<Scrap> getScrapRecord_Manager() {
        List<Scrap> list = new ArrayList<>();
        list = scrapDAO.searchScrapRecordDAO_Manager();
        return list;
    }*/
}
