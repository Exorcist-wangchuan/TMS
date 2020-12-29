package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import po.ProcessRecord;
import po.PurchaseRecord;

public class ProcessDAO {

    private Log log = LogFactory.getLog(ProcessDAO.class);

    public void insertProcess(ProcessRecord process){
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();
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

}
