package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import po.Application;

import java.util.List;

public class ApplyDAO extends BaseDAO {
    private Log log = LogFactory.getLog(UserDAO.class);

    public void insertApply(Application apply){
        Session session =  getSession();
        session.beginTransaction();
        try {
            session.save(apply);
        }catch (RuntimeException re){
            log.error("insert new apply failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<Application> searchApply(){
        Session session =  getSession();
        session.beginTransaction();

        String hql = "from Application as apply";

        try {
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
