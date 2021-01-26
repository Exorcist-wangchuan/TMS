package service;
import com.opensymphony.xwork2.ActionContext;
import dao.IProcessDAO;
import dao.IScrapDAO;
import dao.ProcessDAO;
import dao.ScrapDAO;
import po.ProcessRecord;
import po.PurchaseRecord;
import po.Scrap;
import po.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ScrapRecordService implements IScrapRecordService {
    private IScrapDAO scrapDAO=null;
    private IProcessDAO processDAO=null;

    public void setScrapDAO(IScrapDAO scrapDAO) {
        this.scrapDAO = scrapDAO;
    }

    public void setIProcessDAO(IProcessDAO processDAO) {
        this.processDAO = processDAO;
    }

    public IScrapDAO getIScrapDAO() {
        return scrapDAO;
    }

    public IProcessDAO getIProcessDAO() {
        return processDAO;
    }

    public boolean saveScrap(Scrap scrap){
        //加入时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        //session
        User user = (User) ActionContext.getContext().getSession().get("user");
        scrap.setUID(user.getId());
        //调用DAO
        scrapDAO.insertScrap(scrap);
        return true;
    }

    //监管员获取报废记录
    public List<Scrap> getScrapRecord() {
        List<Scrap> list = new ArrayList<>();
        list = scrapDAO.searchScrapRecordDAO();
        //对报废记录中的使用时间进行处理
        for (Scrap record : list) {
            int count = record.getLifecount();
            if (count < 5) {
                count = count * 8;
            } else if (count < 7) {
                count = count * 8 * 5;
            } else {
                int week = count / 7;
                count = count % 7;
                count = (int) (count * 8 + week * 5 * 0.9);
            }
            record.setLifecount(count);
        }
        return list;
    }

    //经理获取报废记录
    public List<Scrap> getScrapRecord_Manager() {
        List<Scrap> list = new ArrayList<>();
        list = scrapDAO.searchScrapRecordDAO_Manager();
        //对报废记录中的使用时间进行处理
        for (Scrap record : list) {
            int count = record.getLifecount();
            if (count < 5) {
                count = count * 8;
            } else if (count < 7) {
                count = count * 8 * 5;
            } else {
                int week = count / 7;
                count = count % 7;
                count = (int) (count * 8 + week * 5 * 0.9);
            }
            record.setLifecount(count);
        }
        return list;
    }

    //指定seqid查询
    public Scrap getScrapRecordByCodeandId(String pk){
        Scrap scrap =new Scrap();
        scrap=scrapDAO.getScrapRecordByCodeandSeqID(pk);
        return scrap;
    }

    //获取平均报废时限
    public double getAverageScrapTime(String code){
        List<Scrap> list = scrapDAO.getAllRecord();
        double total = 0, gaussian;
        int count = 0;
        for (Scrap record : list) {
            if (record.getCode_seqid().getCode().equals(code)) {
                total = total + record.getLifecount();
                count++;
            }
        }
        if (count==0){
            total = 1;
            count = 1;
        }
        Random random = new Random();
        double []g = new double[3];
        for (int i=0;i<3;i++){
            g[i] = random.nextGaussian()+1;
        }
        gaussian = (g[0] + g[1] + g[2])/3;
        return (total / count) * gaussian;
    }

}
