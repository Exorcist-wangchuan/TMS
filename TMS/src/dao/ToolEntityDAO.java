package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import po.PurchaseRecord;
import po.ToolEntity;

public class ToolEntityDAO extends BaseDAO  {
    private Log log = LogFactory.getLog(PurchaseRecordDAO.class);

    //插入记录（采购入库流程）
    public void insertToolEntityDAO(ToolEntity toolEntity){
        Session session =  getSession();
        session.beginTransaction();
        try {
            session.save(toolEntity);
        }catch (RuntimeException re){
            log.error("insert new apply failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //删除记录（报废流程）
    public void deleteToolEntityDAO(ToolEntity toolEntity){}

}
