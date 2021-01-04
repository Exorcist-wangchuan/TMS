package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.DefineTool;
import po.ProcessRecord;
import po.PurchaseRecord;

public class DefineToolDAO extends BaseDAO {
    private Log log = LogFactory.getLog(DefineToolDAO.class);

    //搜索记录
    public DefineTool searchDefineTool(int id) {
        Session session = getSession();
        session.beginTransaction();
        try {

            String hql = "from DefineTool as d where d.id=:id";
            Query queryObject = (Query) session.createQuery(hql);
            queryObject.setParameter("id", id);
            return (DefineTool) queryObject.uniqueResult();

        } catch (RuntimeException re) {
            log.error("get definetool failed", re);
            throw re;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //插入记录
    public void insertDefineToolDAO(DefineTool defineTool) {
        Session session = getSession();
        session.beginTransaction();
        try {
            session.save(defineTool);
        } catch (RuntimeException re) {
            log.error("insert new apply failed", re);
            throw re;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //更新记录
    public void updateDefineTool(DefineTool defineTool) {
        Session session = getSession();
        session.beginTransaction();
        try {
            session.update(defineTool);
        } catch (RuntimeException re) {
            log.error("update definetool failed", re);
            throw re;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
