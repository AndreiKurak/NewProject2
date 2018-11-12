package lib.connectors;

import org.hibernate.SessionFactory;

public class SessionFactoryGetter {

    private static SessionFactory sessionFactory;

    private SessionFactoryGetter() {}

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory1) {
        if (sessionFactory == null)
            sessionFactory = sessionFactory1;
    }
}
