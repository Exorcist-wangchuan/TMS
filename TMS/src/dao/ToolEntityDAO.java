package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.ProcessRecord;
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
    public void deleteToolEntityDAO(String pk){
        Session session = getSession();
        session.beginTransaction();
        try {
            //core
            //System.out.println();

            //分割code和seqID
            String[] temp=pk.split("&");
            String code=temp[0];
            int seqID=Integer.parseInt(temp[1]);
            System.out.println(code);
            System.out.println(seqID);

            String hql = "delete from ToolEntity where code_seqid.code = :code and code_seqid.seqID = :seqID";
            Query queryObject = (Query) session.createQuery(hql);
            queryObject.setParameter("code", code);
            queryObject.setParameter("seqID", seqID);
            queryObject.executeUpdate();

        }catch (RuntimeException re){
            log.error("get processRecord failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //获得记录
    public ToolEntity getToolEntityByCodeandSeqID(String pk){
        Session session = getSession();
        session.beginTransaction();
        try {
            //core
            System.out.println(pk);

            //分割code和seqID
            String[] temp=pk.split("&");
            String code=temp[0];
            int seqID=Integer.parseInt(temp[1]);

            String hql = "from ToolEntity as t where t.code_seqid.code=:code  and t.code_seqid.seqID = :seqID";
            Query queryObject = (Query) session.createQuery(hql);
            queryObject.setParameter("code", code);
            queryObject.setParameter("seqID", seqID);
            return (ToolEntity) queryObject.uniqueResult();

        }catch (RuntimeException re){
            log.error("get processRecord failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //修改记录
    public void updateToolEntity(ToolEntity toolEntity){
        Session session = getSession();
        session.beginTransaction();
        try {
            session.update(toolEntity);
        }catch (RuntimeException re){
            log.error("update processRecord failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

}
