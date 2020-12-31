package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import po.ProcessRecord;

import java.util.List;


public class ProcessDAO extends BaseDAO {

    private Log log = LogFactory.getLog(ProcessDAO.class);


    public void insertProcess(ProcessRecord process){
        Session session = getSession();
        session.beginTransaction();
        try {
            session.save(process);
        }catch (RuntimeException re){
            log.error("insert new apply failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public void updateProcess(ProcessRecord processRecord){
        Session session = getSession();
        session.beginTransaction();
        try {
            session.update(processRecord);
        }catch (RuntimeException re){
            log.error("update processRecord failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //to do
    public ProcessRecord getProcessBySeqID(int seqID){
        Session session = getSession();
        session.beginTransaction();
        try {
            //core
            System.out.println(seqID);
            String hql = "select pro from PurchaseRecord as p, ProcessRecord as pro where p.seqID = :seqID and p.eID = pro.eID";
            Query queryObject = (Query) session.createQuery(hql);
            queryObject.setParameter("seqID", seqID);
            return (ProcessRecord) queryObject.uniqueResult();
            
        }catch (RuntimeException re){
            log.error("get processRecord failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //Scrap process to do
    public ProcessRecord getScrapProcessBySeqID(int seqID){
        Session session = getSession();
        session.beginTransaction();
        try {
            //core
            System.out.println(seqID);
            String hql = "select pro from Scrap as s, ProcessRecord as pro where s.seqID = :seqID and s.eID = pro.eID";
            Query queryObject = (Query) session.createQuery(hql);
            queryObject.setParameter("seqID", seqID);
            return (ProcessRecord) queryObject.uniqueResult();
        }catch (RuntimeException re){
            log.error("get processRecord failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

}
