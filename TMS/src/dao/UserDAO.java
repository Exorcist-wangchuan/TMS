package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
}
