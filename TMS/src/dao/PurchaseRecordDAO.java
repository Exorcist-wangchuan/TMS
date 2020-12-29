package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import po.PurchaseRecord;

import java.util.List;



public class PurchaseRecordDAO {
    private Log log = LogFactory.getLog(PurchaseRecordDAO.class);

    public void insertPurchaseRecordDAO(PurchaseRecord purchaseRecord){
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        try {
            session.save(purchaseRecord);
        }catch (RuntimeException re){
            log.error("insert new apply failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //监管员获取采购记录
    public List<PurchaseRecord> searchPurchaseRecordDAO(){
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        try {

            String hql = "from PurchaseRecord as p";
            String queryString = hql;
            Query queryObject = session.createQuery(queryString);
            return queryObject.list();
        }catch (RuntimeException re){
            log.error("search apply failed",re);
            throw  re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //经理获取采购记录
    public List<PurchaseRecord> searchPurchaseRecordDAO_Manager(){
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        try {

            String hql = "select p from PurchaseRecord as p,ProcessRecord as pro where p.eID=pro.eID " +
                    "and pro.dname='purchase' and pro.finish = 2";
            String queryString = hql;
            Query queryObject = session.createQuery(queryString);
            return queryObject.list();
        }catch (RuntimeException re){
            log.error("search apply failed",re);
            throw  re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

}
