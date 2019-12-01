package com.homethings.homethingsboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
public class ServletConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.setServletContext(container);
        container.setRequestCharacterEncoding("UTF-8");
        container.setResponseCharacterEncoding("UTF-8");

//        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(context));
//        servlet.setLoadOnStartup(-1);
//        servlet.addMapping("/");
    }
}