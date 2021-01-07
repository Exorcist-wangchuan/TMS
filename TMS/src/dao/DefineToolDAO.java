package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //删除记录
    public void deleteDefineTool(int id){
        Session session = getSession();
        session.beginTransaction();
        try {

            String hql = "delete from DefineTool where id = :id ";
            Query queryObject = (Query) session.createQuery(hql);
            queryObject.setParameter("id", id);
            queryObject.executeUpdate();

        }catch (RuntimeException re){
            log.error("delete definetool failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //批量获取记录
    public List<DefineTool> searchDefineTool_supervisor(){
        Session session =  getSession();
        session.beginTransaction();
        try {
            String hql = "select s from DefineTool as s";
            String queryString = hql;
            Query queryObject = session.createQuery(queryString);
            return queryObject.list();
        }catch (RuntimeException re){
            log.error("search definetool failed",re);
            throw  re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //获取类别和责任人的映射
    public List<DefineTool> getOwnerAndCode(){
        Session session = getSession();
        session.beginTransaction();
        try {
            String hql = "select d from DefineTool as d";
            Query query = session.createQuery(hql);
            return query.list();
        }catch (RuntimeException re){
            log.error("get owner and code error", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

}
