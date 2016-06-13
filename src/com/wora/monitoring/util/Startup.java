package com.wora.monitoring.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.wora.monitoring.servlets.UserSessionManager;


/**
 * @version 1.0
 * @author wora
 */
public class Startup implements ServletContextListener {

	private Logger log = Logger.getLogger(Startup.class);

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {

		ServletContext context = contextEvent.getServletContext();

		//user audit icin eklendi.
		UserSessionManager userSessionManager = UserSessionManager.getInstance();
		context.setAttribute("UserSessionManager", userSessionManager);

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		//....
		//....
	}


}
