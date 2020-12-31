package service;
import com.opensymphony.xwork2.ActionContext;
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

public class ScrapRecordService {
    private ScrapDAO scrapDAO=null;
    private ProcessDAO processDAO=null;

    public ScrapDAO getScrapDAO() {
        return scrapDAO;
    }

    public void setScrapDAO(ScrapDAO scrapDAO) {
        this.scrapDAO = scrapDAO;
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
        return list;
    }

    //经理获取报废记录
    public List<Scrap> getScrapRecord_Manager() {
        List<Scrap> list = new ArrayList<>();
        list = scrapDAO.searchScrapRecordDAO_Manager();
        return list;
    }
}
