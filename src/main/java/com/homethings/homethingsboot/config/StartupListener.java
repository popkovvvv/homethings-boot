//package com.homethings.homethingsboot.config;
//
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//@WebListener
//public class StartupListener implements ServletContextListener {
//    @Override
//    public void contextInitialized(ServletContextEvent event) {
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
//        event.getServletContext().setAttribute("factory", factory);
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent event) {
//        EntityManagerFactory factory = getFactory(event.getServletContext());
//        if (factory != null) {
//            factory.close();
//        }
//    }
//
//    public static EntityManagerFactory getFactory(ServletContext context) {
//        return (EntityManagerFactory) context.getAttribute("factory");
//    }
//}
