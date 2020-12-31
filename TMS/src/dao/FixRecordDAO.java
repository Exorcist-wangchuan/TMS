package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.FixRecord;
import po.PurchaseRecord;

import java.util.List;

public class FixRecordDAO extends BaseDAO
{
    private Log log = LogFactory.getLog(FixRecordDAO.class);

    public void insertFixRecordDAO(FixRecord fixRecord){
        Session session = getSession();
        session.beginTransaction();
        try {
            session.save(fixRecord);
        }catch (RuntimeException re){
            log.error("insert new apply failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //高级用户获取报修记录
    public List<FixRecord> searchFixRecordDAO(){
        Session session =  getSession();
        session.beginTransaction();
        try {
            String hql = "select p from FixRecord as p, ProcessRecord as pro where p.eID = pro.eID and pro.finish = 1";
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


}
