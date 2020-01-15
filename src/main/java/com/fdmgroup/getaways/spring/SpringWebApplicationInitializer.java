package com.fdmgroup.getaways.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.fdmgroup.getaways.configuration.WebConfig;

public class SpringWebApplicationInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = 
				new AnnotationConfigWebApplicationContext();
		
		context.register(WebConfig.class);
		
		ServletRegistration.Dynamic registration = 
				servletContext.addServlet("dispatcher",	new DispatcherServlet(context));
		registration.setLoadOnStartup(1);
//		registration.addMapping("/css/*");
		registration.addMapping("/");
	}

}
