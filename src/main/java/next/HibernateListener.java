package next;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by granknight on 2016. 7. 7..
 */
//@WebListener //some articles on the web mentioned, that this would add the
//Listener automatically to the app context, but i cant believe that this works in my case
public class HibernateListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        HibernateUtil.getSessionFactory(); // create a factory
    }

    public void contextDestroyed(ServletContextEvent event) {
        HibernateUtil.getSessionFactory().close(); // free resources
    }
}
