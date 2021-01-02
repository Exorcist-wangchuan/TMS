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
    public ProcessRecord getPurchaseProcessByCodeandSeqID(String pk){
        Session session = getSession();
        session.beginTransaction();
        try {
            //core
            System.out.println(pk);

            //分割code和seqID
            String[] temp=pk.split("&");
            String code=temp[0];
            int seqID=Integer.parseInt(temp[1]);

            String hql = "select pro from PurchaseRecord as p, ProcessRecord as pro where p.code=:code  and p.seqID = :seqID and  p.eID = pro.eID";
            Query queryObject = (Query) session.createQuery(hql);
            queryObject.setParameter("code", code);
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
    public ProcessRecord getScrapProcessByCodeandSeqID(String pk){
        Session session = getSession();
        session.beginTransaction();
        try {
            //core
            System.out.println(pk);

            //分割code和seqID
            String[] temp=pk.split("&");
            String code=temp[0];
            int seqID=Integer.parseInt(temp[1]);

            String hql = "select pro from Scrap as s, ProcessRecord as pro where s.code=:code and s.seqID = :seqID and s.eID = pro.eID";
            Query queryObject = (Query) session.createQuery(hql);
            queryObject.setParameter("code", code);
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

    //查询报修表和流程表
    public ProcessRecord getFixProcessByCodeandSeqID(String eID){
        Session session = getSession();
        session.beginTransaction();
        try {
            //core
            System.out.println(eID);
            String hql = "select pro from FixRecord as p, ProcessRecord as pro where p.eID = :eID and p.eID = pro.eID";
            Query queryObject = (Query) session.createQuery(hql);
            queryObject.setParameter("eID", eID);
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
