package com.wora.monitoring.servlets;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

public class UserSessionManager {

	private ConcurrentHashMap<String, HttpSession> syncUserMap;
	private static UserSessionManager instance;

	public UserSessionManager() {
		syncUserMap = new ConcurrentHashMap();
	}

	public static UserSessionManager getInstance() {
		if (instance == null)
			instance = new UserSessionManager();
		return instance;
	}

	public boolean isExist(String username) {
		if (syncUserMap.containsKey(username)) {
			return true;
		}
		return false;
	}

	public void addUser(String username, HttpSession session) {
		syncUserMap.put(username, session);
	}

	public boolean addSession(HttpSession session) {
		if (syncUserMap.containsKey(session.getAttribute("username"))) {
			return false;
		}
		syncUserMap.put(session.getAttribute("username").toString(), session);
		return true;
	}

	public HttpSession getSession(String username) {
		return (HttpSession)syncUserMap.get(username);
	}

	public boolean removeSession(String username) {
		if (!syncUserMap.containsKey(username)) {
			return false;
		}
		syncUserMap.remove(username);
		return true;
	}
}