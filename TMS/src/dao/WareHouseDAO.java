package dao;

import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.*;

import java.util.List;

public class WareHouseDAO extends BaseDAO implements IWareHouseDAO {
    private Log log = LogFactory.getLog(WareHouseDAO.class);
    //插入记录（入库流程）
    @Override
    public void insertWareHouseDAO(WareHouseRecord warehouseRecord){
        Session session =  getSession();
        session.beginTransaction();
        try {
            //查询夹具信息后自动插入
            /*ToolEntityDAO toolEntityDAO = null;
            String codeAndSeqID=warehouseRecord.getCode()+"&"+warehouseRecord.getSeqID();
            ToolEntity toolEntity=toolEntityDAO.getToolEntityByCodeandSeqID(codeAndSeqID);
            warehouseRecord.setLocation(toolEntity.getLocation());
            warehouseRecord.setYN(false);*/

            //获取高级用户id
            User sv = (User) ActionContext.getContext().getSession().get("user");
            int id = sv.getId();
            warehouseRecord.setManager(id);
            //System.out.println(warehouseRecord.getCode_seqid().getCode());
            session.save(warehouseRecord);
        }catch (RuntimeException re){
            log.error("insert new apply failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public List<WareHouseRecord> getAllWareHouse(){
        Session session =  getSession();
        session.beginTransaction();
        try {
            String hql = "from WareHouseRecord as w where w.YN = false";
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

    @Override
    public WareHouseRecord getWareHouseRecordByCodeandSeqID(String pk){
        Session session = getSession();
        session.beginTransaction();
        try {
            //core
            System.out.println(pk);
            //分割code和seqID
            String[] temp=pk.split("&");
            String code=temp[0];
            int seqID=Integer.parseInt(temp[1]);
            String hql = "from WareHouseRecord as w where w.code_seqid.Code =:code and w.code_seqid.SeqID =:seqID";
            Query queryObject = (Query) session.createQuery(hql);
            queryObject.setParameter("code",code);
            queryObject.setParameter("seqID",seqID);
            return (WareHouseRecord) queryObject.uniqueResult();
        }catch (RuntimeException re){
            log.error("get processRecord failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //修改记录
    @Override
    public void updateWareHouseRecord(WareHouseRecord wareHouseRecord){
        Session session = getSession();
        session.beginTransaction();
        try {
            wareHouseRecord.setYN(true);
            session.update(wareHouseRecord);
        }catch (RuntimeException re){
            log.error("update processRecord failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
