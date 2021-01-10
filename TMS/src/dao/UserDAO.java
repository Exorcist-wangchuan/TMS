package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.User;

import java.util.List;

public class UserDAO extends BaseDAO implements IUserDAO {
    private Log log = LogFactory.getLog(UserDAO.class);

    @Override
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

    @Override
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

    //系统管理员获取用户列表
    @Override
    public List<User> searchManageDAO(){
        Session session =  getSession();
        session.beginTransaction();
        try {
            String hql = "select p from User as p ";
            Query queryObject = session.createQuery(hql);
            return queryObject.list();
        }catch (RuntimeException re){
            log.error("search users failed",re);
            throw  re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //删除用户
    @Override
    public void delete(User user){
        Session session = getSession();
        session.beginTransaction();
        try {
            session.delete(user);
        }catch (RuntimeException re){
            log.error("delete user failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //修改用户权限
    @Override
    public void update(User user)
    {
        Session session = getSession();
        session.beginTransaction();
        try {
            session.update(user);
        }catch (RuntimeException re){
            log.error("update user failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //增加用户
    @Override
    public void insert(User user)
    {
        Session session = getSession();
        session.beginTransaction();
        try {
            session.save(user);
        }catch (RuntimeException re){
            log.error("add user failed", re);
            throw re;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

}
