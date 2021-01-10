package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class BaseDAO implements IBaseDAO {
    private SessionFactory sessionFactory;

    @Override
    public Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
