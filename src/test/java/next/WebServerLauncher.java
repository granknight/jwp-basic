package next;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServerLauncher {
	private static final Logger logger = LoggerFactory.getLogger(WebServerLauncher.class);
	
	public static void main(String[] args) throws Exception {
        String webappDirLocation = "webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

//        Context rootCtx = tomcat.addContext("", base.getAbsolutePath());
//        rootCtx.getServletContext().addListener("com.example.listeners.HibernateListener");

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        logger.info("configuring app with basedir: {}", new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}
