package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.PeriodCheck;

import java.util.List;


public class PeriodCheckDAO extends BaseDAO{
    private Log log = LogFactory.getLog(PeriodCheckDAO.class);
    public List getPeriodCheckList(){
        Session session = getSession();
        session.beginTransaction();
        try {
            Query query = session.createQuery("from PeriodCheck");
            return query.list();
        }catch (RuntimeException re){
            log.error("get period check list error", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public void insertPeriodCheck(PeriodCheck periodCheck){
        Session session = getSession();
        session.beginTransaction();
        try {
            session.save(periodCheck);
        }catch (RuntimeException re){
            log.error("insert new periodCheck error", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public int getPMPeriod(String code){
        Session session = getSession();
        session.beginTransaction();
        try {
            String hql = "select pmPeriod from DefineTool as d where code=:code";
            Query queryObject = (Query) session.createQuery(hql);

            queryObject.setParameter("code", code);
            if(queryObject.uniqueResult()==null)
                return -1;
            return (int) queryObject.uniqueResult();
        }catch (RuntimeException re){
            log.error("get PMPeriod error");
            throw re;
        }
        finally {
            session.getTransaction().commit();
            session.close();
        }
    }

}
