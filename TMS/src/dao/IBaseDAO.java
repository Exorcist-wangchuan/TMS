package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface IBaseDAO {
    Session getSession();

    void setSessionFactory(SessionFactory sessionFactory);
}
