package util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Data {

	
	private static Data singleton = new Data( );	
	private Data() { }
	private Map<String, String> sessions = new HashMap<>();
	
	
	public static Data newInstance( ) {
		return singleton;
	}
	
	public void addSession(HttpSession session, String email) {
		sessions.put(session.getId(), email);
	}
	
	public Map<String, String> getSessions()
	{
		return sessions;
	}
	
	public Boolean isUserLoggedIn(HttpServletRequest request)
	{
		if (sessions.containsKey(request.getSession().getId()))
		{
			return true;
		}
		
		return false;
	}
	
	public String getEmailBySessionID(String sessionID)
	{
		return sessions.get(sessionID);
	}
}
