package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import po.PurchaseRecord;
import po.Scrap;

import java.util.List;

public class ScrapDAO extends BaseDAO {
    private Log log = LogFactory.getLog(ScrapDAO.class);
    public void insertScrap(Scrap scrap){
        Session session=getSession();
        session.beginTransaction();
        try {
            session.save(scrap);
        }catch (RuntimeException re){
            log.error("insert new scrap failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

/*    public List<Scrap> searchScrap(){
        Session session = getSession();
        session.beginTransaction();

        String hql = "from Scrap as scrap";

        try {
            String queryString = hql;
            Query queryObject = session.createQuery(queryString);
            return queryObject.list();
        }catch (RuntimeException re){
            log.error("search scrap failed",re);
            throw  re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }*/

    //监管员获取报废记录
    public List<Scrap> searchScrapRecordDAO(){
        Session session =  getSession();
        session.beginTransaction();
        try {
            String hql = "select s from Scrap as s, ProcessRecord as pro where s.eID = pro.eID and pro.finish = 1 and pro.dname='scrap'";
            org.hibernate.query.Query queryObject = session.createQuery(hql);
            return queryObject.list();
        }catch (RuntimeException re){
            log.error("search apply failed",re);
            throw  re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //经理获取报废记录
    public List<Scrap> searchScrapRecordDAO_Manager(){
        Session session =  getSession();
        session.beginTransaction();
        try {
            String hql = "select s from Scrap as s,ProcessRecord as pro where s.eID=pro.eID " +
                    "and pro.dname='scrap' and pro.finish = 2";
            String queryString = hql;
            org.hibernate.query.Query queryObject = session.createQuery(queryString);
            return queryObject.list();
        }catch (RuntimeException re){
            log.error("search scrap failed",re);
            throw  re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
