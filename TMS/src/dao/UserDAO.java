package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.User;

import java.util.List;

public class UserDAO extends BaseDAO {
    private Log log = LogFactory.getLog(UserDAO.class);

    public List findByHql(String hql) {
        log.debug("finding LoginUser instance by hql");
        Session session = getSession();
        try {
            String queryString = hql;
            Query queryObject = session.createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by hql failed", re);
            throw re;
        } finally {
            session.close();
        }
    }

    public User getUserByID(int id){
        Session session = getSession();
        session.beginTransaction();
        try {
            String hql = "select u from User as u where id=:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            return (User) query.uniqueResult();
        }catch (RuntimeException re){
            log.error("get user email error");
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

}
