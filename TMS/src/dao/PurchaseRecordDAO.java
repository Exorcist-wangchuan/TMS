package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import po.ProcessRecord;
import po.PurchaseRecord;

import java.util.List;

public class PurchaseRecordDAO extends BaseDAO {
    private Log log = LogFactory.getLog(PurchaseRecordDAO.class);

    public void insertPurchaseRecordDAO(PurchaseRecord purchaseRecord){
        Session session =  getSession();
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

    //通过seqid查询
    public PurchaseRecord getPurchaseRecordBySeqID(int id){
        Session session = getSession();
        session.beginTransaction();
        try {
            //core
            System.out.println(id);
            String hql = "from PurchaseRecord as p where p.seqID = :id";
            Query queryObject = (Query) session.createQuery(hql);
            queryObject.setParameter("id", id);
            return (PurchaseRecord) queryObject.uniqueResult();

        }catch (RuntimeException re){
            log.error("get processRecord failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //监管员获取采购记录
    public List<PurchaseRecord> searchPurchaseRecordDAO(){
        Session session =  getSession();
        session.beginTransaction();
        try {
            String hql = "select p from PurchaseRecord as p, ProcessRecord as pro where p.eID = pro.eID and pro.finish = 1";
            Query queryObject = session.createQuery(hql);
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
        Session session =  getSession();
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
