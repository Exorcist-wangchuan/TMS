package service;

import com.opensymphony.xwork2.ActionContext;
import dao.IProcessDAO;
import dao.IScrapDAO;
import po.Scrap;
import po.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IScrapRecordService {
    public void setScrapDAO(IScrapDAO scrpDAO);
    public void setIProcessDAO(IProcessDAO processDAO);
    public IScrapDAO getIScrapDAO();
    public IProcessDAO getIProcessDAO();
    public boolean saveScrap(Scrap scrap);
    public List<Scrap> getScrapRecord();
    public List<Scrap> getScrapRecord_Manager();
    public Scrap getScrapRecordByCodeandId(String pk);

}
