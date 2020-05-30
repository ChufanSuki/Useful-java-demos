package com.allen;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener()
public class DataManagerListener implements ServletContextListener,HttpSessionListener {

    // Public constructor is required by servlet spec
    public DataManagerListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute(DataManager.ATTRIBUTE_NAME, new DataManager());
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context
         (the Web application) is undeployed or
         Application Server shuts down.
      */
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute(CartModel.ATTRIBUTE_NAME, new CartModel());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
