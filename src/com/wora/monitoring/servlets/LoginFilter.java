package com.wora.monitoring.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LoginFilter implements Filter {

	Logger logger = Logger.getLogger(LoginFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filter) throws IOException, ServletException {
		logger.info("LoginFilter is started..");

		boolean authorized = false;

		if (req instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) req;
			if (request.getSession() != null) {

				if (request.getUserPrincipal() != null) {
					authorized = true;

					try {

						String username = request.getUserPrincipal().getName();
						// aynı kullanıcı farklı yerlerden login oluyorsa oncekini invalidate edelim.
						UserSessionManager sessionManager = (UserSessionManager) request.getServletContext().getAttribute("UserSessionManager");
						HttpSession oldSession = sessionManager.getSession(username);
						if (oldSession != null) {
							logger.debug("Session already created. Old session will be invalidate..");
							oldSession.invalidate();
						} else {
							logger.debug("New Session added to UserSessionManager..");
							sessionManager.addUser(username, request.getSession());
						}

					} catch (Exception e) {
						logger.error(e, e);
					}

				}

			}
		}

		logger.debug("Request forwarding..");
		filter.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	private String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
