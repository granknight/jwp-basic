package next;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by granknight on 2016. 7. 7..
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            //should i call .configure() on the returned Configuration here?
            sessionFactory = getConfiguration()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    private static Configuration getConfiguration(){
        Configuration c = new Configuration();

        c.setProperty("hibernate.connection.url", "jdbc:hsqldb:hsql://localhost:1234/mydb1");
        c.setProperty("hibernate.connection.username", "SA");
        c.setProperty("hibernate.connection.password", "");
        c.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");


        c.setProperty("dialect", "org.hibernate.dialect.HSQLDialect");
        c.setProperty("cache.provider_class", "org.hibernate.cache.NoCacheProvider");
        c.setProperty("cache.use_query_cache", "false");
        c.setProperty("cache.use_minimal_puts", "false");
        c.setProperty("max_fetch_depth", "3");

        c.setProperty("show_sql", "true");
        c.setProperty("format_sql", "true");
        c.setProperty("hbm2ddl.auto", "create");

        c.addPackage("com.example.models");
        //c.addAnnotatedClass(MyClass.class);


        return c;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
